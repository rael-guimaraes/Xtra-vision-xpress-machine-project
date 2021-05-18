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
public class RegularPayment implements Payment {
    // charge regular price

    @Override
    public boolean pay(Rent rent) {
        return BankSoftwareSimulation.receiveFromClient(REGULAR_VALUE);
    }

}
