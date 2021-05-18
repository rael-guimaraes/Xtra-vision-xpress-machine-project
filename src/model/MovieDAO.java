/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbconnection.DBConnector;
import entities.Movie;
import exceptions.DataException;
import exceptions.InsertionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raelg
 */
public class MovieDAO {
// get the availables movies

    public List<Movie> getMovies() throws DataException {
        List<Movie> movieList = new ArrayList<>();
        try {
            // get connection to the database
            Connection conn = DBConnector.getConnection();
            // get a statment from the connection
            Statement stmt = conn.createStatement();
            String query = "select * from movies where is_available = true; ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("movie_id"));
                movie.setName(rs.getString("name"));
                movie.setGender(rs.getString("gender"));
                movie.setReleaseDate(rs.getObject("release_date", LocalDate.class));
                movie.setIsAvailable(rs.getBoolean("is_available"));
                movieList.add(movie);
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
// set movie status to unavailable after being rented

    public void updateMovieStatus(int movieId) throws InsertionException {
        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE movies SET is_available = false WHERE movie_id = " + movieId + ";";
            boolean rs = stmt.execute(query);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InsertionException("Sorry, something went wrong operation unsuccessful");

        }
    }

}
