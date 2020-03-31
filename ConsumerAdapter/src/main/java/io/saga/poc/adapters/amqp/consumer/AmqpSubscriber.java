package io.saga.poc.adapters.amqp.consumer;

import io.saga.poc.entities.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AmqpSubscriber {
    private static final Logger LOG = LoggerFactory.getLogger(AmqpSubscriber.class);

    @Autowired
    @Qualifier("executionEventHandler")
    EventHandler eventHandler;

    @RabbitListener(queues = "#{eventQueue.name}")
    public void process(ServiceRequest request) {
        LOG.trace("Subscribed event: {}", request);
        LOG.trace("Handled by: {}", eventHandler.getClass());
        eventHandler.processEvent(request);
    }
}
