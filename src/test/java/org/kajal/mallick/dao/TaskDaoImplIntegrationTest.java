package org.kajal.mallick.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajal.mallick.SpringBootAssignmentApplication;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.repositories.ParentTaskRepository;
import org.kajal.mallick.repositories.TaskRepository;
import org.kajal.mallick.util.TaskManagerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringBootAssignmentApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@IfProfileValue(name = "live-external-tests-enabled", value = "true")
@Sql("/sql/createTask.sql")
public class TaskDaoImplIntegrationTest {

    String TASK_NAME = "Task 1";

    String PARENT_TASK_NAME = "Parent Task 1";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ParentTaskRepository parentTaskRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        Assert.assertNotNull(tasks);
        Assert.assertTrue(1 <= tasks.size());
        Assert.assertEquals(TASK_NAME, tasks.get(0).getTaskName());
    }

    @Test
    public void findTaskById() {
        Task task = taskRepository.findById(1l).get();
        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, task.getTaskName());
    }

    @Test
    public void findAllParentTasks() {

        List<ParentTask> parentTasks = parentTaskRepository.findAll();
        Assert.assertNotNull(parentTasks);
        Assert.assertTrue(1 <= parentTasks.size());
        Assert.assertEquals("IPB", parentTasks.get(0).getParentTaskName());
    }

    @Test
    public void saveTask() {
        ParentTask parentTask = new ParentTask();
        parentTask.setParentId(1);

        Task task = new Task();
        task.setTaskName(TASK_NAME);
        task.setPriority(1);
        task.setStatus(TaskManagerConstant.STATUS_OPEN);
        task.setEndDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setParentTask(parentTask);


        Task savedTask = taskRepository.save(task);
        Assert.assertNotNull(savedTask);
        Assert.assertEquals(TaskManagerConstant.STATUS_OPEN, savedTask.getStatus());
    }
}