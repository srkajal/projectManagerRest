package org.kajal.mallick.exception;

public class UserException extends RuntimeException {
    private String errorMessage;

    public UserException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public UserException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
