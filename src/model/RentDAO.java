/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Card;
import entities.Rent;
import exceptions.DataException;
import exceptions.InsertionException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author raelg
 */
public class RentDAO {
// get the quantitie of rented movies for the used card

    public List<Rent> getRentListByCardID(Card card) throws DataException {
        List<Rent> movieList = new ArrayList<>();
        try {
            // get connection to the database
            Connection conn = dbconnection.DBConnector.getConnection();
            // get a statment from the connection
            Statement stmt = conn.createStatement();
            String query = "select * from rent as rs "
                    + "inner join card as c on rs.card_id = c.card_id where c.card_number ='" + card.getCardNumber() + "' and rs.has_returned = false;";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Rent rentedMovie = new Rent();

                rentedMovie.setId(rs.getInt("rent_id"));

                movieList.add(rentedMovie);

            }

            // close the result set, the statment and the connection
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Sorry, unavailable connection! please, try again later");

        }
        return movieList;

    }
// insert the rent details into the database

    public Rent insertRentDetails(Rent rent) throws InsertionException {

        try {
            Connection conn = dbconnection.DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO rent (card_id , rent_date, email) VALUES  ('" + rent.getCard().getId() + "','" + rent.getRentDate() + "','" + rent.getEmail() + "');";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                rent.setId(rs.getInt(1));
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
        return rent;
    }
// insert the rent_id and movie_id into the rented_movie table

    public void insertRentedMovie(int rentId, int movieId) throws InsertionException {
        try {
            Connection conn = dbconnection.DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "insert into rented_movie (movie_id, rent_id) values ('" + movieId + "','" + rentId + "');";
            boolean rs = stmt.execute(query);

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
    }

}
