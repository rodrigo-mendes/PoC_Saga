package io.saga.orchestrator.invoice.process;

import io.saga.poc.entities.BusinessEntity;
import io.saga.poc.entities.ServiceRequest;
import io.saga.poc.entities.ServiceResponse;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessUtil {
    private static final String SC_ERROR = "SC_ERROR";

    private ProcessUtil() {
    }

    public static void processResponse(DelegateExecution ctx, ServiceResponse serviceResponse) throws Exception {
        ctx.setVariable(ProcessConstants.VAR_RESPONSE, serviceResponse);
        if (!Response.Status.OK.toString().equals(serviceResponse.getStatusCode())) {
            ProcessContext pctx = (ProcessContext) ctx.getVariable(ProcessConstants.VAR_CTX);
            pctx.setError(serviceResponse.getErrorMessage());
            throw new BpmnError(SC_ERROR);
        }
    }
}

