package org.kajal.mallick.exception;

public class ProjectException extends RuntimeException {
    private String errorMessage;

    public ProjectException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ProjectException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
