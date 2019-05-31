package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonClassDescription;

@JsonClassDescription("response_details")
public class BaseResponse {
    private String status;
    private Integer code;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public BaseResponse(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
