package io.saga.orchestrator.invoice.activity.compensation;

import io.saga.orchestrator.invoice.process.ProcessConstants;
import io.saga.orchestrator.invoice.process.ProcessUtil;
import io.saga.poc.adapters.amqp.producer.AmqpRpcClient;
import io.saga.poc.entities.BusinessEntity;
import io.saga.poc.entities.ServiceRequest;
import io.saga.poc.entities.ServiceResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TicketGenerationCompensationActivity implements JavaDelegate {
    private static final Logger LOG = LoggerFactory.getLogger(TicketGenerationCompensationActivity.class);
    public static final String SERVICE_ACTION = "Ticket Generation Compensation Activity";

    @Autowired
    AmqpRpcClient amqpRpcClient;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOG.info("############################## {} ################################", this.getClass().getName());
        LOG.info("execute {} - {}", ProcessConstants.ACTIVITY_NAME_TICKET_GENERATION_COMPENSATION, SERVICE_ACTION);
        LOG.info("calling {} - {}", ProcessConstants.SERVICE_NAME_TICKET_GENERATION_COMPENSATION, SERVICE_ACTION);
        BusinessEntity sc = (BusinessEntity) execution.getVariable(ProcessConstants.INVOICE);
        ServiceRequest sr = new ServiceRequest().withId(UUID.randomUUID().toString())
                                                .withCreatedBy(ProcessConstants.ACTIVITY_NAME_TICKET_GENERATION_COMPENSATION)
                                                .withCreatedDate(new Date())
                                                .withServiceName(ProcessConstants.SERVICE_NAME_TICKET_GENERATION_COMPENSATION)
                                                .withServiceAction(SERVICE_ACTION);
        LOG.info("RPC Request - {}", sr);
        ServiceResponse response = amqpRpcClient.invokeService(sr);
        LOG.info("RPC Response.getStatusCode() - {}", response.getStatusCode());
        ProcessUtil.processResponse(execution, response);
        LOG.info("RPC Response - {}", response);
        LOG.info("############################## {} ################################", this.getClass().getName());
    }
}