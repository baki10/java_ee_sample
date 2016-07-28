package com.bakigoal.cinema.batch;

import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import com.bakigoal.cinema.entities.Sales;

/**
 *
 * @author ilmir
 */
@Named
@Dependent
public class SalesProcessor implements ItemProcessor {

    @Override
    public Sales processItem(Object s) {
        Sales sales = new Sales();
        StringTokenizer tokens = new StringTokenizer((String) s, ",");
        sales.setId(Integer.parseInt(tokens.nextToken()));
        sales.setAmount(Float.parseFloat(tokens.nextToken()));
        return sales;
    }

}
