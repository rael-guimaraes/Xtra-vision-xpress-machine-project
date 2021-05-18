/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import entities.Rent;
import model.BankSoftwareSimulation;

/**
 *
 * @author raelg
 */
public class FullPenaltyPayment implements Payment {
// charge movie full price

    private static final double FINE_VALUE = 15.00;

    @Override
    public boolean pay(Rent rent) {

        double chargeValue = FINE_VALUE;

        return BankSoftwareSimulation.receiveFromClient(chargeValue);
    }

}
