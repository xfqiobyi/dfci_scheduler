package com.dfci.scheduler.resources.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorEntity {

    public final String errorCode;
    public final String errorMessage;

    public ErrorEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_message")
    public String getErrorMessage() {
        return errorMessage;
    }
}
