package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserTest {
    @InjectMocks
    private User user;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getUserId() {
        user.setUserId(1l);
        assertEquals(1l, user.getUserId());
    }

    @Test
    public void getFirstName() {
        user.setFirstName("name1");
        assertEquals("name1", user.getFirstName());
    }

    @Test
    public void getLastName() {
        user.setLastName("name1");
        assertEquals("name1", user.getLastName());
    }

    @Test
    public void getEmployeeId() {
        user.setEmployeeId(12);
        assertEquals(12, user.getEmployeeId());
    }

    @Test
    public void getProject() {
        Project project = new Project();
        project.setProjectId(2l);

        user.setProject(project);
        assertEquals(2l, user.getProject().getProjectId());
    }

    @Test
    public void getTask() {
        Task task = new Task();
        task.setTaskId(3l);

        user.setTask(task);
        assertEquals(3l, user.getTask().getTaskId());
    }

    @Test
    public void equals1() {
        ParentTask parentTask1 = new ParentTask(1l);
        parentTask1.setParentTaskName("task2");

        Project project1 = new Project(4l);
        Task task1 = new Task(1l, parentTask1, project1, "task1", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 12), 1, "status1");
        Task task2 = new Task(2l, parentTask1, project1, "task1", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 12), 1, "status1");


        project1.setProjectId(1l);
        project1.setProjectName("Project Name");
        project1.setStartDate(LocalDate.ofYearDay(2019, 12));
        project1.setEndDate(LocalDate.ofYearDay(2019, 12));
        project1.setPriority(1);

        Project project2 = new Project();

        project1.setProjectId(2l);
        project1.setProjectName("Project Name");
        project1.setStartDate(LocalDate.ofYearDay(2019, 12));
        project1.setEndDate(LocalDate.ofYearDay(2019, 12));
        project1.setPriority(1);

        User user1 = new User();
        User user2 = new User();

        user1.setUserId(1l);
        user1.setEmployeeId(12);
        user1.setFirstName("name1");
        user1.setLastName("name2");
        user1.setTask(task1);
        user1.setProject(project1);

        user2.setUserId(1l);
        user2.setEmployeeId(12);
        user2.setFirstName("name1");
        user2.setLastName("name2");
        user2.setTask(task1);
        user2.setProject(project1);

        assertTrue(user1.equals(user2));

        assertTrue(user1.equals(user1));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(new Object()));
        assertTrue(user1.equals(user2));

        user2.setTask(task2);
        assertFalse(user1.equals(user2));

        user2.setProject(project2);
        assertFalse(user1.equals(user2));

        user2.setLastName("name4");
        assertFalse(user1.equals(user2));

        user2.setFirstName("name5");
        assertFalse(user1.equals(user2));

        user2.setEmployeeId(15);
        assertFalse(user1.equals(user2));

        user2.setUserId(2l);
        assertFalse(user1.equals(user2));
    }

    @Test
    public void hashCode1() {
        assertTrue(user.hashCode() > 0);
    }

    @Test
    public void toString1() {
        assertNotNull(user.toString());
    }
}