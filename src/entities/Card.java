/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author raelg
 */
public class Card {
    
    private int id;
    private String cardNumber;
    private String expiryDate;
    private String authentication;
    
     public Card() {
    }
   
     public Card(int id){
         this.id = id;
     }

    public Card(String cardNumber, String exparationDate, String authentication) {
        this.cardNumber = cardNumber;
        this.expiryDate = exparationDate;
        this.authentication = authentication;
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
