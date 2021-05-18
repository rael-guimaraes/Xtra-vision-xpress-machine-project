/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Movie;
import exceptions.DataException;
import exceptions.InsertionException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.MovieDAO;

/**
 *
 * @author raelg
 */
public class MovieController {

    private MovieDAO movieDAO;

    public MovieController() {
        movieDAO = new MovieDAO();
    }
// insert the available movies to be displayed

    public DefaultTableModel insertMoviesIntoDefaultTableModel(String[] columnNames) throws DataException {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        List<Movie> movieList = movieDAO.getMovies();

        movieList.stream().forEach(Movie -> {
            Object[] tableItens = {Movie, Movie.getId(), Movie.getName(), Movie.getGender(), Movie.getReleaseDate()};
            tableModel.addRow(tableItens);

        });

        return tableModel;
    }
// set the rented movie to unavailable

    public void updateRentedMovieStatus(int movieId) throws InsertionException {
        movieDAO.updateMovieStatus(movieId);
    }

}
