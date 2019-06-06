package org.kajal.mallick.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskExceptionTest {

    private TaskException taskException;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        taskException = new TaskException("Error message");
    }

    @Test
    public void getErrorMessage() {

        assertEquals("Error message", taskException.getErrorMessage());
    }
}