package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskTest {
    @InjectMocks
    private Task task;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getTaskId() {
        task.setTaskId(1l);
        assertEquals(1l, task.getTaskId());
    }

    @Test
    public void getParentTask() {
        ParentTask parentTask = new ParentTask(1l);
        task.setParentTask(parentTask);
        assertEquals(1l, task.getParentTask().getParentId());
    }

    @Test
    public void getTaskName() {
        task.setTaskName("task1");
        assertEquals("task1", task.getTaskName());
    }

    @Test
    public void getStartDate() {
        task.setStartDate(LocalDate.ofYearDay(2019, 12));

        assertEquals(2019, task.getStartDate().getYear());
    }

    @Test
    public void getEndDate() {
        task.setEndDate(LocalDate.ofYearDay(2019, 12));

        assertEquals(2019, task.getEndDate().getYear());
    }

    @Test
    public void getPriority() {
        task.setPriority(1);
        assertEquals(1, task.getPriority());
    }

    @Test
    public void getStatus() {
        task.setStatus("status1");
        assertEquals("status1", task.getStatus());
    }

    @Test
    public void equals1() {
        ParentTask parentTask1 = new ParentTask(1l);
        ParentTask parentTask2 = new ParentTask(1l);
        ParentTask parentTask3 = new ParentTask(2l);
        parentTask1.setParentTaskName("task2");
        parentTask2.setParentTaskName("task2");
        Task task1 = new Task(1l, parentTask1, "task1", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 12), 1, "status1");
        Task task2 = new Task(1l, parentTask2, "task1", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 12), 1, "status1");

        assertTrue(task1.equals(task2));

        assertTrue(task1.equals(task1));
        assertFalse(task1.equals(null));
        assertFalse(task1.equals(new Object()));
        assertTrue(task1.equals(task2));

        task2.setStatus("status3");
        assertFalse(task1.equals(task2));

        task2.setEndDate(LocalDate.ofYearDay(2019, 16));
        assertFalse(task1.equals(task2));

        task2.setStartDate(LocalDate.ofYearDay(2019, 13));
        assertFalse(task1.equals(task2));

        task2.setTaskName("task3");
        assertFalse(task1.equals(task2));

        task2.setParentTask(parentTask3);
        assertFalse(task1.equals(task2));

        task2.setPriority(2);
        assertFalse(task1.equals(task2));

        task2.setTaskId(2l);
        assertFalse(task1.equals(task2));

    }

    @Test
    public void hashCode1() {
        assertTrue(task.hashCode() > 0);
    }

    @Test
    public void toString1() {
        assertNotNull(task.toString());
    }
}