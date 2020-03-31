package io.saga.orchestrator.invoice.process;

import io.saga.poc.entities.BusinessEntity;
import io.saga.poc.entities.ErrorMessage;

import java.io.Serializable;

public class ProcessContext implements Serializable {
    private static final long serialVersionUID = 1L;
    private BusinessEntity response;

    public BusinessEntity getResponse() {
        return response;
    }

    public void setResponse(BusinessEntity response) {
        this.response = response;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    private ErrorMessage error;
}