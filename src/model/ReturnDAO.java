/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbconnection.DBConnector;
import entities.Rent;
import exceptions.DataException;
import exceptions.InsertionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author raelg
 */
public class ReturnDAO {
// set returned movie status to available

    public void insertReturnedMovie(int movieId) throws InsertionException {
        try {
            Connection conn = dbconnection.DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE movies SET is_available = true WHERE movie_id = " + movieId + ";";
            boolean rs = stmt.execute(query);

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
    }
// get the rent time to check for possible extra charges 

    public Rent getRentDetails(int movieId) throws DataException {
        Rent rent = new Rent();
        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "select * from rent as r "
                    + "inner join rented_movie as rm on r.rent_id = rm.rent_id "
                    + "where movie_id = " + movieId + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                rent.setId(rs.getInt("rent_id"));
                rent.setRentDate(rs.getObject("rent_date", LocalDate.class));
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Sorry, unavailable connection! please, try again later");

        }
        return rent;
    }
// get rent details to apply possible maximum 

    public Rent getRentDateForMaximumPenalty() throws InsertionException {
        Rent rent = new Rent();
        try {
            // get connection to the database
            Connection conn = DBConnector.getConnection();
            // get a statment from the connection
            Statement stmt = conn.createStatement();
            String query = "select * from rent where has_returned = false ;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                rent.setId(rs.getInt("rent_id"));
                rent.setRentDate(rs.getObject("rent_date", LocalDate.class));
            }
            // close the result set, the statment and the connection
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, unavailable connection! please, try again later");

        }
        return rent;
    }
// set the rent status has_returned to true for the returned movie

    public void updateRentedMovieStatus(int movieId) throws InsertionException {
        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE rent as r "
                    + "inner join rented_movie as rm on r.rent_id = rm.rent_id "
                    + " SET r.has_returned = true  "
                    + " where rm.movie_id = " + movieId + ";";
            boolean rs = stmt.execute(query);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
    }

}
