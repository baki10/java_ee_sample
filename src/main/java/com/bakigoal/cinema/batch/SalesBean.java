package com.bakigoal.cinema.batch;

import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bakigoal.cinema.entities.Sales;

/**
 *
 * @author ilmir
 */
@Named
@RequestScoped
@Stateful
public class SalesBean {

    @PersistenceContext
    private EntityManager em;

    public void runJob() {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("eod-sales", new Properties());
            System.out.println("Started job: with id: " + jobId);
        } catch (JobStartException ex) {
        }
    }

    public List<Sales> getSalesData() {
        return em.createNamedQuery("Sales.findAll", Sales.class)
                .getResultList();
    }
}
