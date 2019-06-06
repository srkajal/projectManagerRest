package org.kajal.mallick.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserExceptionTest {
    private UserException userException;

    @Before
    public void setUp() throws Exception {
        userException = new UserException("Error message");
    }

    @Test
    public void getErrorMessage() {
        assertEquals("Error message", userException.getErrorMessage());
    }
}