package io.saga.poc.adapters.amqp.consumer;

import io.saga.poc.entities.ServiceResponse;
import io.saga.poc.entities.ServiceRequest;

public interface EventHandler {
    public ServiceResponse processRequest(ServiceRequest request);

    public void processEvent(ServiceRequest request);
}
