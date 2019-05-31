package org.kajal.mallick.exception;

public class TaskException extends RuntimeException {
    private String errorMessage;

    public TaskException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public TaskException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
