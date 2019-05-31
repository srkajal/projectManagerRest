package org.kajal.mallick.exception;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestResponseEntityExceptionHandlerTest {
    @InjectMocks
    RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

    @Mock
    private HttpHeaders headers;
    @Mock
    private WebRequest request;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private FieldError error;
    @Mock
    private ObjectError objError;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(error));
        when(bindingResult.getGlobalErrors()).thenReturn(Collections.singletonList(objError));

        assertEquals(HttpStatus.BAD_REQUEST, restResponseEntityExceptionHandler.handleMethodArgumentNotValid(ex, headers, HttpStatus.HTTP_VERSION_NOT_SUPPORTED, request).getStatusCode());

    }

    @Test
    public void handleMissingServletRequestParameter() {
        MissingServletRequestParameterException ex = mock(MissingServletRequestParameterException.class);
        assertEquals(HttpStatus.BAD_REQUEST, restResponseEntityExceptionHandler.handleMissingServletRequestParameter(ex, headers, HttpStatus.BAD_REQUEST, request).getStatusCode());

    }

    @Test
    public void handleConstraintViolation() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);

        when(ex.getConstraintViolations()).thenReturn(Collections.singleton(violation));
        when(violation.getRootBeanClass()).thenReturn(Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, restResponseEntityExceptionHandler.handleConstraintViolation(ex, request).getStatusCode());

    }

    @Test
    public void handleMethodArgumentTypeMismatch() {

        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);

        assertEquals(HttpStatus.BAD_REQUEST, restResponseEntityExceptionHandler.handleMethodArgumentTypeMismatch(ex, request).getStatusCode());


    }

    @Test
    public void handleConflict() {
        RuntimeException ex = mock(RuntimeException.class);

        assertEquals(HttpStatus.CONFLICT, restResponseEntityExceptionHandler.handleConflict(ex, request).getStatusCode());

    }

    @Test
    public void exceptionHandler() {
        RuntimeException ex = mock(RuntimeException.class);

        assertEquals(HttpStatus.OK, restResponseEntityExceptionHandler.exceptionHandler(ex).getStatusCode());
    }

    @Test
    public void exceptionHandler1() {
        Exception ex = mock(RuntimeException.class);

        assertEquals(HttpStatus.OK, restResponseEntityExceptionHandler.exceptionHandler(ex).getStatusCode());
    }
}