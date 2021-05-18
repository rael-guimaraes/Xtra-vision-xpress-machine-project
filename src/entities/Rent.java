/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raelg
 */
public class Rent {
    
    private int id;
    private List<Movie> movieList;
    private Card card;
    private LocalDate rentDate;
    private boolean hasReturned;
    private String email;
    

    public Rent() {
    }

    public Rent(int id, Card card, LocalDate rentDate, boolean hasReturned, String email) {
        this.id = id;
        this.card = card;
        this.rentDate = rentDate;
        this.hasReturned = hasReturned;
        this.email = email;
    }

    public Rent( LocalDate rentDate, String email) {
        this.rentDate = rentDate;
        this.email = email;
    }
    
    public Rent( Card card, LocalDate rentDate) {
        this.card = card;
        this.rentDate = rentDate;
    }

    public Rent( LocalDate rentDate) {
        this.rentDate = rentDate;
    }
    
    public Rent(int id, Card card, LocalDate rentDate, boolean hasReturned) {
        this.id = id;
        this.card = card;
        this.rentDate = rentDate;
        this.hasReturned = hasReturned;
    }

    public Rent(String movie) {
        this.email = movie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public boolean getHasReturned() {
        return hasReturned;
    }

    public void setHasReturned(boolean hasReturned) {
        this.hasReturned = hasReturned;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
    
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    
    
    
    
    
}
