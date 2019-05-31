package org.kajal.mallick.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskDtoTest {

    private TaskDto taskDto;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        taskDto = new TaskDto();
    }

    @Test
    public void getTaskId() {
        taskDto.setTaskId(1l);
        assertEquals(1l, taskDto.getTaskId());
    }

    @Test
    public void getParentTaskDto() {
        taskDto.setParentTaskDto(new ParentTaskDto(1l, "task1"));
        assertEquals("task1", taskDto.getParentTaskDto().getParentTask());
    }

    @Test
    public void getTask() {
        taskDto.setTask("Task1");
        assertEquals("Task1", taskDto.getTask());
    }

    @Test
    public void getStartDate() {
        taskDto.setStartDate("01/01/2019");
        assertEquals("01/01/2019", taskDto.getStartDate());
    }

    @Test
    public void getEndDate() {
        taskDto.setEndDate("01/01/2019");
        assertEquals("01/01/2019", taskDto.getEndDate());
    }

    @Test
    public void getPriority() {
        taskDto.setPriority(12);
        assertEquals(12, taskDto.getPriority());
    }

    @Test
    public void getStatus() {
        taskDto.setStatus("Success");
        assertEquals("Success", taskDto.getStatus());
    }
}