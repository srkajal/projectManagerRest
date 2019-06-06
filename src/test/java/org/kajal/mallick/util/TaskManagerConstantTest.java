package org.kajal.mallick.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TaskManagerConstantTest {
    @Test
    public void allTest() {
        assertNotNull(TaskManagerConstant.USERS_FAILURE_MESSAGE);
        assertNotNull(TaskManagerConstant.USERS_SUCCESS_MESSAGE);
        assertNotNull(TaskManagerConstant.TASKS_FAILURE_MESSAGE);
        assertNotNull(TaskManagerConstant.TASKS_SUCCESS_MESSAGE);
        assertNotNull(TaskManagerConstant.STATUS_OPEN);
        assertNotNull(TaskManagerConstant.STATUS_CLOSED);
    }

}