/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Card;
import exceptions.DataException;
import exceptions.InsertionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author raelg
 */
public class CardDAO {
// register new user card number

    public Card registerCard(Card card) throws InsertionException {

        try {
            Connection conn = dbconnection.DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO card (card_number) VALUES  ('" + card.getCardNumber() + "');";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                card.setId(rs.getInt(1));
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
        return card;
    }
// get card by number to check if its a new customer

    public Card getRegisteredCardByCardNumber(Card card) throws DataException {

        try {
            Connection conn = dbconnection.DBConnector.getConnection();
            // get a statment from the connection
            Statement stmt = conn.createStatement();
            String query = "select * from card where card_number = '" + card.getCardNumber() + "';";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                card.setId(rs.getInt("card_id"));
                card.setCardNumber(rs.getString("card_number"));

            }

            // close the result set, the statment and the connection
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Sorry, unavailable connection! please, try again later");

        }
        return card;
    }

}
