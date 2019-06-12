package org.kajal.mallick.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.dao.TaskDao;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.model.request.CreateTaskRequest;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.UpdateTaskRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskFacadeImplTest {

    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskFacadeImpl taskManagerFacade;
    @Mock
    private TaskDao taskDao;
    private Task task;
    private ParentTask parentTask;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        task = new Task();
        task.setTaskName(TASK_NAME);

        parentTask = new ParentTask();
        parentTask.setParentTaskName(TASK_NAME);
    }

    @Test
    public void findAllTasks() {
        when(taskDao.findAllTasks()).thenReturn(Collections.singletonList(task));

        List<Task> tasks = taskManagerFacade.findAllTasks();

        Assert.assertNotNull(tasks);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(TASK_NAME, tasks.get(0).getTaskName());
    }

    @Test
    public void findTaskById() {
        when(taskDao.findTaskById(anyLong())).thenReturn(task);
        Task task = taskManagerFacade.findTaskById(1l);
        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, task.getTaskName());
    }

    @Test
    public void saveTask() {
        when(taskDao.saveTask(any(Task.class))).thenReturn(task);
        when(userDao.findByUserId(anyLong())).thenReturn(new User());
        when(userDao.updateTask(anyLong(), anyLong())).thenReturn(1);
        Task savedTask = taskManagerFacade.saveTask(new CreateTaskRequest());

        Assert.assertNotNull(savedTask);
        Assert.assertEquals(TASK_NAME, savedTask.getTaskName());
    }

    @Test
    public void update() {
        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest(1l, 2l, "Task", LocalDate.now(), LocalDate.now(), 3);
        when(taskDao.updateTaskDetails(anyString(), any(LocalDate.class), any(LocalDate.class), anyInt(), anyLong(), anyLong())).thenReturn(1);
        int rowUpdated = taskManagerFacade.update(updateTaskRequest);

        Assert.assertTrue(rowUpdated > 0);
    }

    @Test
    public void closeTaskById() {
        when(taskDao.updateTaskStatus(anyString(), anyLong())).thenReturn(1);
        int upadtedRow = taskManagerFacade.closeTaskById(1l);

        Assert.assertTrue(upadtedRow > 0);
    }

    @Test
    public void findAllParentTasks() {
        when(taskDao.findAllParentTasks()).thenReturn(Collections.singletonList(parentTask));
        List<ParentTask> parentTasks = taskManagerFacade.findAllParentTasks();
        Assert.assertNotNull(parentTasks);
        Assert.assertEquals(1, parentTasks.size());
        Assert.assertEquals(TASK_NAME, parentTasks.get(0).getParentTaskName());
    }

    @Test
    public void saveParentTask() {
        when(taskDao.saveParentTask(any(ParentTask.class))).thenReturn(parentTask);
        ParentTask savedPrentTask = taskManagerFacade.saveParentTask(new ParentTaskRequest());

        Assert.assertNotNull(savedPrentTask);
        Assert.assertEquals(TASK_NAME, savedPrentTask.getParentTaskName());
    }
}