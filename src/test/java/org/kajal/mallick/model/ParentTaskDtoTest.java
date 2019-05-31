package org.kajal.mallick.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParentTaskDtoTest {
    @InjectMocks
    private ParentTaskDto parentTaskDto;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getParentId() {
        parentTaskDto.setParentId(1l);
        assertEquals(1l, parentTaskDto.getParentId());
    }

    @Test
    public void getParentTask() {
        parentTaskDto.setParentTask("task1");
        assertEquals("task1", parentTaskDto.getParentTask());
    }
}