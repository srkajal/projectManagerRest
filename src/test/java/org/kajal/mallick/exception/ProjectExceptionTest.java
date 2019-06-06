package org.kajal.mallick.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectExceptionTest {
    private ProjectException projectException;

    @Before
    public void setUp() throws Exception {
        projectException = new ProjectException("Error message");
    }

    @Test
    public void getErrorMessage() {
        assertEquals("Error message", projectException.getErrorMessage());
    }
}