/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Card;
import exceptions.DataException;
import exceptions.InsertionException;
import model.CardDAO;

/**
 *
 * @author raelg
 */
public class CardController {

    private CardDAO cardDAO;

    public CardController() {
        cardDAO = new CardDAO();
    }
    // register the user card number in the first transaction

    public Card registerCard(Card card) throws InsertionException {
        return cardDAO.registerCard(card);
    }
    // check if it is trhe first transaction for the user card

    public boolean isExitingCard(Card card) throws DataException {
        return cardDAO.getRegisteredCardByCardNumber(card).getId() != 0;

    }
}
