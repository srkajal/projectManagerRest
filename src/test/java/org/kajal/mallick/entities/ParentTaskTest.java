package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParentTaskTest {
    @InjectMocks
    private ParentTask parentTask;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getParentId() {
        parentTask.setParentId(1l);
        assertEquals(1l, parentTask.getParentId());
    }

    @Test
    public void getParentTaskName() {
        parentTask.setParentTaskName("task1");
        assertEquals("task1", parentTask.getParentTaskName());
    }

    @Test
    public void equals1() {
        ParentTask parentTask1 = new ParentTask();
        ParentTask parentTask2 = new ParentTask();

        parentTask1.setParentId(2l);
        parentTask1.setParentTaskName("task2");

        parentTask2.setParentId(2l);
        parentTask2.setParentTaskName("task2");

        assertTrue(parentTask1.equals(parentTask1));
        assertFalse(parentTask1.equals(null));
        assertFalse(parentTask1.equals(new Object()));
        assertTrue(parentTask1.equals(parentTask2));

    }

    @Test
    public void hashCode1() {
        assertTrue(parentTask.hashCode() > 0);
    }

    @Test
    public void toString1() {
        assertNotNull(parentTask.toString());
    }
}