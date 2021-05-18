/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Rent;
import exceptions.InsertionException;
import java.time.LocalDate;
import model.ReturnDAO;
import payment.FullPenaltyPayment;
import payment.Payment;
import payment.PenaltyPayment;
import payment.RegularPayment;
import utilityClasses.XpressMachineConstantes;

/**
 *
 * @author raelg
 */
public class PaymentController {

    private ReturnDAO returnDAO;
    private Payment payment;

    public PaymentController() {
        returnDAO = new ReturnDAO();

    }
// charge regular movie value

    public boolean executeRegularRent(Rent rent) {
        payment = new RegularPayment();
        return payment.pay(rent);
    }
// charge penalty for user who are rurning the movie after the due date

    public void executeRentPenalty(Rent rent) {
        payment = new PenaltyPayment();
        payment.pay(rent);
    }
// charge movie full price

    public void executeMaximumPenalty() throws InsertionException {
        Rent rent = returnDAO.getRentDateForMaximumPenalty();
        LocalDate dueDate = rent.getRentDate();

        if (dueDate.compareTo(LocalDate.now()) >= XpressMachineConstantes.MAX_TIME_BEFORE_FULL_PENALTY) {
            payment = new FullPenaltyPayment();
            payment.pay(rent);
        }

    }

}
