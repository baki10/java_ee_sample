/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakigoal.cinema.booking;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.bakigoal.cinema.entities.Movie;
import com.bakigoal.cinema.entities.ShowTiming;

/**
 *
 * @author ilmir
 */
@Named
@FlowScoped("booking")
public class Booking implements Serializable{

    @PersistenceContext
    EntityManager em;

    int movieId;
    String startTime;
    int startTimeId;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        StringTokenizer tokens = new StringTokenizer(startTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        this.startTime = tokens.nextToken();
    }

    public int getStartTimeId() {
        return startTimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        try {
            return em.createNamedQuery("Movie.findById", Movie.class)
                    .setParameter("id", movieId).getSingleResult()
                    .getName();
        } catch (NoResultException e) {
            return "";
        }
    }

    public String getTheater() {
        // for a movie and show
        try {
            // Always return the first theater
            List<ShowTiming> list
                    = em.createNamedQuery("ShowTiming.findByMovieAndTimeslotId",
                            ShowTiming.class)
                    .setParameter("movieId", movieId)
                    .setParameter("timeslotId", startTimeId)
                    .getResultList();
            if (list.isEmpty()) {
                return "none";
            }
            return list
                    .get(0)
                    .getTheater()
                    .getId().toString();
        } catch (NoResultException e) {
            return "none";
        }
    }
}
