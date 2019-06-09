package org.kajal.mallick.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

class ErrorResponse {
    private HttpStatus status;
    @JsonProperty("error_message")
    private String errorMessage;
    private List<String> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus status, String errorMessage, List<String> errors) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
