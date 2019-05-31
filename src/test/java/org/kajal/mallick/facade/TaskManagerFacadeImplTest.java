package org.kajal.mallick.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.dao.TaskManagerDao;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskManagerFacadeImplTest {

    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskManagerFacadeImpl taskManagerFacade;
    @Mock
    private TaskManagerDao taskManagerDao;
    private Task task;
    private ParentTask parentTask;

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
        when(taskManagerDao.findAllTasks()).thenReturn(Collections.singletonList(task));

        List<Task> tasks = taskManagerFacade.findAllTasks();

        Assert.assertNotNull(tasks);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(TASK_NAME, tasks.get(0).getTaskName());
    }

    @Test
    public void findTaskById() {
        when(taskManagerDao.findTaskById(anyLong())).thenReturn(task);
        Task task = taskManagerFacade.findTaskById(1l);
        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, task.getTaskName());
    }

    @Test
    public void saveTask() {
        when(taskManagerDao.saveTask(any(Task.class))).thenReturn(task);
        Task savedTask = taskManagerFacade.saveTask(new TaskRequest());

        Assert.assertNotNull(savedTask);
        Assert.assertEquals(TASK_NAME, savedTask.getTaskName());
    }

    @Test
    public void update() {
        when(taskManagerDao.saveTask(any(Task.class))).thenReturn(task);
        Task savedTask = taskManagerFacade.update(new TaskRequest());

        Assert.assertNotNull(savedTask);
        Assert.assertEquals(TASK_NAME, savedTask.getTaskName());
    }

    @Test
    public void closeTaskById() {
        when(taskManagerDao.updateTaskStatus(anyString(), anyLong())).thenReturn(1);
        int upadtedRow = taskManagerFacade.closeTaskById(1l);

        Assert.assertTrue(upadtedRow > 0);
    }

    @Test
    public void findAllParentTasks() {
        when(taskManagerDao.findAllParentTasks()).thenReturn(Collections.singletonList(parentTask));
        List<ParentTask> parentTasks = taskManagerFacade.findAllParentTasks();
        Assert.assertNotNull(parentTasks);
        Assert.assertEquals(1, parentTasks.size());
        Assert.assertEquals(TASK_NAME, parentTasks.get(0).getParentTaskName());
    }

    @Test
    public void saveParentTask() {
        when(taskManagerDao.saveParentTask(any(ParentTask.class))).thenReturn(parentTask);
        ParentTask savedPrentTask = taskManagerFacade.saveParentTask(new ParentTaskRequest());

        Assert.assertNotNull(savedPrentTask);
        Assert.assertEquals(TASK_NAME, savedPrentTask.getParentTaskName());
    }
}