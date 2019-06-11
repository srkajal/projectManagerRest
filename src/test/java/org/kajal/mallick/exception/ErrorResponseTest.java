package org.kajal.mallick.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ErrorResponseTest {

    private ErrorResponse errorResponse;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, "Some error", "Error");
    }

    @Test
    public void getStatus() {
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatus());
    }

    @Test
    public void getMessage() {
        errorResponse.setErrorMessage("message1");
        assertEquals("message1", errorResponse.getErrorMessage());
    }

    @Test
    public void getErrors() {
        errorResponse.setErrors(Collections.singletonList("Error1"));
        assertEquals("Error1", errorResponse.getErrors().get(0));
    }
}