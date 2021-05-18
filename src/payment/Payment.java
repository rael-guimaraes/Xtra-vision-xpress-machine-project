package payment;

import entities.Rent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raelg
 */
public interface Payment {

    double FINE_VALUE = 1.50;
    double REGULAR_VALUE = 2.99;

    boolean pay(Rent rent);

}
