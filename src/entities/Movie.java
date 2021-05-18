/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author raelg
 */
public class Movie {
    
    private int id;
    private String name;
    private String gender;
    private LocalDate releaseDate;
    private boolean isAvailable;

    public Movie(int id, String name, String gender, LocalDate releaseDate, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    
    
}
