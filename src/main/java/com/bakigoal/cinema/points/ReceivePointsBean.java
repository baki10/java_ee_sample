package com.bakigoal.cinema.points;

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;

/**
 *
 * @author ilmir
 */
@Named
@RequestScoped
@JMSDestinationDefinition(name = ReceivePointsBean.JNDI_NAME,
        interfaceName = "javax.jms.Queue")
public class ReceivePointsBean {

    public static final String JNDI_NAME = "java:global/jms/pointsQueue";

    @Inject
    private JMSContext context;

    @Resource(lookup = ReceivePointsBean.JNDI_NAME)
    private Queue pointsQueue;

    public String receiveMessage() {
        String message = context
                .createConsumer(pointsQueue)
                .receiveBody(String.class);
        System.out.println("Received message: " + message);
        return message;
    }

    public int getQueueSize() {
        int count = 0;
        try {
            QueueBrowser browser = context.createBrowser(pointsQueue);
            Enumeration elems = browser.getEnumeration();
            while (elems.hasMoreElements()) {
                elems.nextElement();
                count++;
            }
        } catch (JMSException ex) {
        }
        return count;
    }
}
