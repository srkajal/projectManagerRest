package org.kajal.mallick.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.repositories.ParentTaskRepository;
import org.kajal.mallick.repositories.TaskRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskDaoImplTest {

    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskDaoImpl taskManagerDao;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ParentTaskRepository parentTaskRepository;
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
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));
        List<Task> tasks = taskManagerDao.findAllTasks();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(TASK_NAME, tasks.get(0).getTaskName());
    }

    @Test
    public void findTaskById() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        Task task = taskManagerDao.findTaskById(1l);
        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, task.getTaskName());
    }

    @Test
    public void saveTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task savedTask = taskManagerDao.saveTask(task);

        Assert.assertNotNull(savedTask);
        Assert.assertEquals(TASK_NAME, savedTask.getTaskName());
    }

    @Test
    public void updateTaskStatus() {
        when(taskRepository.updateTaskStatus(anyString(), anyLong())).thenReturn(1);
        int upadtedRow = taskManagerDao.updateTaskStatus("status", 1l);

        Assert.assertTrue(upadtedRow > 0);
    }

    @Test
    public void findAllParentTasks() {
        when(parentTaskRepository.findAll()).thenReturn(Collections.singletonList(parentTask));
        List<ParentTask> parentTasks = taskManagerDao.findAllParentTasks();
        Assert.assertNotNull(parentTasks);
        Assert.assertEquals(1, parentTasks.size());
        Assert.assertEquals(TASK_NAME, parentTasks.get(0).getParentTaskName());
    }

    @Test
    public void saveParentTask() {
        when(parentTaskRepository.save(any(ParentTask.class))).thenReturn(parentTask);
        ParentTask savedPrentTask = taskManagerDao.saveParentTask(parentTask);

        Assert.assertNotNull(savedPrentTask);
        Assert.assertEquals(TASK_NAME, savedPrentTask.getParentTaskName());
    }
}