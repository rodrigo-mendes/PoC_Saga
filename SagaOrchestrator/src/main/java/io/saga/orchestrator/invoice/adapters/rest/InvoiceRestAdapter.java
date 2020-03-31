package io.saga.orchestrator.invoice.adapters.rest;



import io.saga.orchestrator.invoice.process.ProcessConstants;
import io.saga.orchestrator.invoice.process.ProcessContext;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/invoice")
public class InvoiceRestAdapter {
    @Autowired
    private ProcessEngine camunda;

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ResponseEntity<?> invoiceGet() {
        ProcessContext context = new ProcessContext();
        invoiceProcess(context);
        if (context.getError() != null) {
            return new ResponseEntity<>(context.getError(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(context.getResponse(), HttpStatus.OK);

    }

    private ProcessInstance invoiceProcess(ProcessContext context) {
        return camunda.getRuntimeService().startProcessInstanceByKey("invoiceProcess",
                Variables.putValue(ProcessConstants.VAR_CTX, context));
    }
}
