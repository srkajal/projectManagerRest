package org.kajal.mallick.model;

import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.entities.User;
import org.mockito.InjectMocks;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDtoTest {

    @InjectMocks
    private UserDto userDto;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getUserId() {
        userDto.setUserId(2l);
        assertEquals(2l, userDto.getUserId());
    }

    @Test
    public void getFirstName() {
        userDto.setFirstName("name1");
        assertEquals("name1", userDto.getFirstName());
    }

    @Test
    public void getLastName() {
        userDto.setLastName("name2");
        assertEquals("name2", userDto.getLastName());
    }

    @Test
    public void getEmployeeId() {
        userDto.setEmployeeId(3);
        assertEquals(3, userDto.getEmployeeId());
    }

    @Test
    public void getProject() {
        ProjectDto project = new ProjectDto();
        project.setProjectId(1l);
        userDto.setProject(project);

        assertEquals(1l, userDto.getProject().getProjectId());
    }

    @Test
    public void getTask() {

        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(5l);

        userDto.setTask(taskDto);
        assertEquals(5l, userDto.getTask().getTaskId());
    }

    @Test
    public void getUserDtoByUser() {

        Project project = new Project();
        project.setProjectId(6l);

        Task task = new Task();
        task.setTaskId(7l);
        task.setStatus("OPEN");

        project.setTasks(Collections.singletonList(task));

        User user = new User();
        user.setUserId(8l);
        user.setProject(project);
        user.setTask(task);

        UserDto userDto1 = new UserDto(user);
        assertEquals(7l, userDto1.getTask().getTaskId());
        assertEquals(8l, userDto1.getUserId());
        assertEquals(6l, userDto1.getProject().getProjectId());

    }
}