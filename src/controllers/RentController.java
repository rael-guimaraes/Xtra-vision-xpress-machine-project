/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Card;
import entities.Movie;
import entities.Rent;
import exceptions.DataException;
import exceptions.InsertionException;
import exceptions.PaymentException;
import exceptions.ValidationException;
import java.util.List;
import model.RentDAO;
import utilityClasses.XpressMachineConstantes;

/**
 *
 * @author raelg
 */
public class RentController {

    private RentDAO rentDAO;
    private CardController cardController;
    private PaymentController paymentController;
    private MovieController movieController;
    private boolean isSuccessfulPayment;
    private boolean isRegularCustomer;

    public RentController() {
        rentDAO = new RentDAO();
        cardController = new CardController();
        paymentController = new PaymentController();
        movieController = new MovieController();

    }
// inserting each rented movie 

    public void insertRentedMovie(Rent rent) throws InsertionException {
        for (Movie movie : rent.getMovieList()) {
            rentDAO.insertRentedMovie(rent.getId(), movie.getId());
        }
    }
// getting the quantity of rented movies for the used card

    public List<Rent> getRentedMovieQuantity(Card card) throws DataException {
        return rentDAO.getRentListByCardID(card);
    }
// processing/charging the rent for each choosen movie.  

    public void processRent(Rent rent) throws DataException, PaymentException, InsertionException, ValidationException {
        //checking for regular customer
        isRegularCustomer = cardController.isExitingCard(rent.getCard());
        for (Movie movie : rent.getMovieList()) {

            if (isRegularCustomer) {
                processRegularCustomerRent(rent);
            } else {
                processNewCustomerRent(rent);
            }
            movieController.updateRentedMovieStatus(movie.getId());
        }
    }
// executing the regular rent price for regular customer

    public void processRegularCustomerRent(Rent rent) throws DataException, PaymentException, InsertionException, ValidationException {
//  validating the quantity of movies 
        if (getRentedMovieQuantity(rent.getCard()).size() <= XpressMachineConstantes.MAX_RENT_REGULAR_CUSTOMER) {
            isSuccessfulPayment = paymentController.executeRegularRent(rent);
            if (isSuccessfulPayment) {
                rentDAO.insertRentDetails(rent);
                insertRentedMovie(rent);
            } else {
                throw new PaymentException("Sorry, your balance is too low!");
            }
        } else {
            throw new ValidationException("Sorry, the limit of rented movie has been reached! The limit is 4 movies.");
        }

    }
// executing rent for the new customer

    public void processNewCustomerRent(Rent rent) throws InsertionException, ValidationException {
        if (rent.getMovieList().size() <= XpressMachineConstantes.MAX_RENT_FIRST_TIME_CUSTOMER) {
            cardController.registerCard(rent.getCard());

            rentDAO.insertRentDetails(rent);
            insertRentedMovie(rent);
            isRegularCustomer = true;
        } else {
            throw new ValidationException("Sorry, the limit of rented movie has been reached! The limit is 2 movies in the first rental transaction.");
        }

    }

}
