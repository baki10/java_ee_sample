package com.bakigoal.cinema.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bakigoal.cinema.entities.Timeslot;

@Named
@Stateless
@Path("timeslots")
public class TimeslotFacadeREST extends AbstractFacade<Timeslot> {

    @PersistenceContext
    private EntityManager entityManager;

    public TimeslotFacadeREST() {
        super(Timeslot.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Timeslot timeslot) {
        super.create(timeslot);
    }

    @PUT
    @Path("{id}")
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Timeslot timeslot) {
        super.edit(timeslot);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Timeslot find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Timeslot> getAll() {
        return super.getAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Timeslot> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String getCount() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
