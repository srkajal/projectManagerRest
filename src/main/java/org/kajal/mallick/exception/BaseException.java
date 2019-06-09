package org.kajal.mallick.exception;

public class BaseException extends RuntimeException {
    private String status;
    private int code;
    private String errorMessage;

    public BaseException(String status, int code, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public BaseException() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
