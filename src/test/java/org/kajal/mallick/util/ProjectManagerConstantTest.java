package org.kajal.mallick.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ProjectManagerConstantTest {
    @Test
    public void allTest() {
        assertNotNull(ProjectManagerConstant.USERS_FAILURE_MESSAGE);
        assertNotNull(ProjectManagerConstant.USERS_SUCCESS_MESSAGE);
        assertNotNull(ProjectManagerConstant.TASKS_FAILURE_MESSAGE);
        assertNotNull(ProjectManagerConstant.TASKS_SUCCESS_MESSAGE);
        assertNotNull(ProjectManagerConstant.STATUS_OPEN);
        assertNotNull(ProjectManagerConstant.STATUS_CLOSED);
    }

}