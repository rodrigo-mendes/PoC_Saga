package io.saga.microservice.activity;

import io.saga.poc.adapters.amqp.consumer.EventHandler;
import io.saga.poc.entities.ServiceRequest;
import io.saga.poc.entities.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

@Service("compensationEventHandler")
public class BookInvoiceItemsCompensationService implements EventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BookInvoiceItemsCompensationService.class);

    @Override
    public ServiceResponse processRequest(ServiceRequest request) {
        LOG.info("############################## {} ################################", this.getClass().getName());
        LOG.trace("RPC resquest - {} ", request);
        String serviceName = request.getServiceName();
        String serviceAction = request.getServiceAction();
        String requestId = request.getId();
        ServiceResponse response = new ServiceResponse().withId(UUID.randomUUID().toString()).withCreatedBy(request.getServiceName())
                .withCreatedDate(new Date()).withStatusCode(Response.Status.OK.toString())
                .withRelatedRequest(request.getId());
        LOG.trace("RPC response {}", response);
        LOG.info("############################## {} ################################", this.getClass().getName());
        return response;
    }

    @Override
    public void processEvent(ServiceRequest request) {
        LOG.trace("Event is consumed by subscriber.");
    }
}