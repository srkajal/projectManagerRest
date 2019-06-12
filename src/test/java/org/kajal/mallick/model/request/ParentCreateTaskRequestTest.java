package org.kajal.mallick.model.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParentCreateTaskRequestTest {

    private ParentTaskRequest parentTaskRequest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        parentTaskRequest = new ParentTaskRequest(1l, "TaskName");
    }

    @Test
    public void getParentId() {
        long id = 1l;
        parentTaskRequest.setParentId(id);
        assertEquals(id, parentTaskRequest.getParentId());
    }

    @Test
    public void getParentTaskName() {
        String taskName = "task1";
        parentTaskRequest.setParentTaskName(taskName);
        assertEquals(taskName, parentTaskRequest.getParentTaskName());
    }
}