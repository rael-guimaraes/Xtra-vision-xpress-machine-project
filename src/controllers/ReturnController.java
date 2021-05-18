/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Rent;
import exceptions.DataException;
import exceptions.InsertionException;
import java.time.LocalDate;
import model.ReturnDAO;

/**
 *
 * @author raelg
 */
public class ReturnController {

    private ReturnDAO returnDAO;
    private PaymentController paymentController;

    public ReturnController() {
        returnDAO = new ReturnDAO();
        paymentController = new PaymentController();
    }
// returning the movie by its id

    private void returnMovie(int movieId) throws InsertionException {
        returnDAO.insertReturnedMovie(movieId);
    }
// getting the rent time for possible extra charges

    public void getRentedMovieTimeByMovieId(int movieId) throws DataException, InsertionException {
        Rent rent = returnDAO.getRentDetails(movieId);
        LocalDate dueDate = rent.getRentDate().plusDays(1);

        if (LocalDate.now().isAfter(dueDate)) {
            paymentController.executeRentPenalty(rent);
        }
        returnMovie(movieId);

    }

    // set the rent status after returned
    public void updateRentedMovie(int movieId) throws InsertionException {
        returnDAO.updateRentedMovieStatus(movieId);
    }

}
