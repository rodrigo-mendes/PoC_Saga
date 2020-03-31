package io.saga.microservice.activity;

import io.saga.poc.adapters.amqp.consumer.EventHandler;
import io.saga.poc.entities.ErrorMessage;
import io.saga.poc.entities.ServiceRequest;
import io.saga.poc.entities.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

@Service("executionEventHandler")
public class TicketGenerationExecutionService implements EventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TicketGenerationExecutionService.class);

    @Override
    public ServiceResponse processRequest(ServiceRequest request) {
        LOG.info("############################## {} ################################", this.getClass().getName());
        LOG.trace("RPC resquest - {} ", request);
        String serviceName = request.getServiceName();
        String serviceAction = request.getServiceAction();
        String requestId = request.getId();
        ErrorMessage errorMessage = new ErrorMessage().withCode("ERR_BAD_REQUEST").withMessage("Invalid service request.")
        					.withDetails("Invalid service request.");
        ServiceResponse response = new ServiceResponse().withCreatedBy(request.getServiceName()).withCreatedDate(new Date())
                .withStatusCode(Response.Status.FORBIDDEN.toString()).withRelatedRequest(request.getId())
                .withErrorMessage(errorMessage);
//        ServiceResponse response = new ServiceResponse().withId(UUID.randomUUID().toString()).withCreatedBy(request.getServiceName())
//                .withCreatedDate(new Date()).withStatusCode(Response.Status.OK.toString())
//                .withRelatedRequest(request.getId());
        LOG.trace("RPC response {}", response);
        LOG.info("############################## {} ################################", this.getClass().getName());
        return response;
    }

    @Override
    public void processEvent(ServiceRequest request) {
        LOG.trace("Event is consumed by subscriber.");
    }
}