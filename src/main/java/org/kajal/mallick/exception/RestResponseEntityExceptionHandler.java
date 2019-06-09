package org.kajal.mallick.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setErrorMessage(ex.getMessage());
        error.setErrors(errors);
        return handleExceptionInternal(
                ex, error, headers, error.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error;
        if (ex.getRequiredType() != null) {
            error =
                    ex.getName() + " should be of type " + ex.getRequiredType().getName();
        } else {
            error =
                    ex.getName() + " should be of type " + ex.getRequiredType();
        }


        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(BaseException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.PRECONDITION_FAILED, ex.getMessage(), "Error occurred");
        //BaseResponse baseResponse = new BaseResponse(ex);
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.PRECONDITION_FAILED, ex.getMessage(), "Error occurred");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
