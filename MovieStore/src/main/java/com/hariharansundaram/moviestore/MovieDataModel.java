/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hariharansundaram.moviestore;

/**
 *
 * @author asundaram
 */
public class MovieDataModel {
    String title;
    String actor;
    String actress;
    String genre;
    Integer year;
    
    MovieDataModel() {
        
    }
    
    MovieDataModel(String title, String actor, String actress, String genre, int year) {
        this.title = title;
        this.actor = actor;
        this.actress = actress;
        this.genre = genre;
        this.year = year;
    }
    
    public void setData(String title, String actor, String actress, String genre, int year) {
        this.title = title;
        this.actor = actor;
        this.actress = actress;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getActor() {
        return actor;
    }

    public String getActress() {
        return actress;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getYear() {
        return year;
    }
}
