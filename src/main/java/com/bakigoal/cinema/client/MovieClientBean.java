package com.bakigoal.cinema.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.bakigoal.cinema.entities.Movie;
import com.bakigoal.cinema.json.MovieWriter;

/**
 *
 * @author ilmir
 */
@Named
@RequestScoped
public class MovieClientBean {

    private Client client;
    private WebTarget target;

    @Inject
    private MovieBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/cinema/webresources/movies/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Movie[] getMovies() {
        return target
                .request()
                .get(Movie[].class);
    }

    public Movie getMovie() {
        return target.path("{movieId}")
                .resolveTemplate("movieId", bean.getMovieId())
                .request()
                .get(Movie.class);
    }

    public void deleteMovie() {
        target.path("{movieId}")
                .resolveTemplate("movieId", bean.getMovieId())
                .request()
                .delete();
    }

    public void addMovie() {
        Movie movie = new Movie();
        movie.setId(bean.getMovieId());
        movie.setName(bean.getMovieName());
        movie.setActors(bean.getActors());

        target.register(MovieWriter.class)
                .request()
                .post(Entity.entity(movie, MediaType.APPLICATION_JSON));
    }

}
