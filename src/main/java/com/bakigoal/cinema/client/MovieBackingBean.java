package com.bakigoal.cinema.client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ilmir
 */
@Named
@RequestScoped
public class MovieBackingBean {
    
    private int movieId;
    private String movieName;
    private String actors;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
    
}
