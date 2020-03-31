package io.saga.poc.adapters.amqp.consumer;

import io.saga.poc.entities.ServiceResponse;
import io.saga.poc.entities.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AmqpRpcServer {
    private static final Logger LOG = LoggerFactory.getLogger(AmqpRpcServer.class);

    @Autowired
    @Qualifier("executionEventHandler")
    EventHandler executionEventHandler;

    @Autowired
    @Qualifier("compensationEventHandler")
    EventHandler compensationEventHandler;

    @RabbitListener(queues = "#{serviceQueue.name}")
    public ServiceResponse processExecution(ServiceRequest request) {
        LOG.trace("RPC service request: {}", request);
        LOG.trace("Handled by: {}", executionEventHandler.getClass());
        return executionEventHandler.processRequest(request);
    }

    @RabbitListener(queues = "#{compensationQueue.name}")
    public ServiceResponse processCompensation(ServiceRequest request) {
        LOG.trace("RPC service request: {}", request);
        LOG.trace("Handled by: {}", compensationEventHandler.getClass());
        return compensationEventHandler.processRequest(request);
    }
}