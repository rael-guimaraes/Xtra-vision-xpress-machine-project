/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author raelg
 */
public class BankSoftwareSimulation {
    public static boolean receiveFromClient(double value){
       
       Random randomBalance = new Random();
       String x = String.valueOf(randomBalance.nextInt(50));
        
        return Double.parseDouble(x)> value; 
    }    
}
