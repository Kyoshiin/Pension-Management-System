package com.cts.pension.disbursement.model;

public class ProcessPensionResponse {
    private int processPensionStatusCode;

    public ProcessPensionResponse() {
    }

    public ProcessPensionResponse(int processPensionStatusCode) {
        this.processPensionStatusCode = processPensionStatusCode;
    }

    public int getProcessPensionStatusCode() {
        return processPensionStatusCode;
    }

    public void setProcessPensionStatusCode(int processPensionStatusCode) {
        this.processPensionStatusCode = processPensionStatusCode;
    }
}