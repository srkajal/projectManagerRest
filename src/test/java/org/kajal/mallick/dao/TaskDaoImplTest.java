package org.kajal.mallick.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.repositories.ParentTaskRepository;
import org.kajal.mallick.repositories.TaskRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class TaskDaoImplTest {

    private final Task taskTestObject;
    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskDaoImpl taskManagerDao;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ParentTaskRepository parentTaskRepository;

    public TaskDaoImplTest(Task taskTestObject) {
        this.taskTestObject = taskTestObject;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with TASK={0}")
    public static Iterable<Object[]> data() {
        return BaseDataTest.taskData;
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllTasks() {
        when(taskRepository.findAllTasks()).thenReturn(Collections.singletonList(taskTestObject));
        List<Task> tasks = taskManagerDao.findAllTasks();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(taskTestObject.getTaskName(), tasks.get(0).getTaskName());
    }

    @Test
    public void findTaskById() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskTestObject));
        Task task = taskManagerDao.findTaskById(1l);
        Assert.assertNotNull(task);
        Assert.assertEquals(taskTestObject.getTaskName(), task.getTaskName());
    }

    @Test
    public void saveTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(taskTestObject);
        Task savedTask = taskManagerDao.saveTask(taskTestObject);

        Assert.assertNotNull(savedTask);
        Assert.assertEquals(taskTestObject.getTaskName(), savedTask.getTaskName());
    }

    @Test
    public void updateTaskStatus() {
        when(taskRepository.updateTaskStatus(anyString(), anyLong())).thenReturn(1);
        int upadtedRow = taskManagerDao.updateTaskStatus(taskTestObject.getStatus(), taskTestObject.getTaskId());

        assertTrue(upadtedRow > 0);
    }

    @Test
    public void findAllParentTasks() {
        when(parentTaskRepository.findAll()).thenReturn(Collections.singletonList(taskTestObject.getParentTask()));
        List<ParentTask> parentTasks = taskManagerDao.findAllParentTasks();
        Assert.assertNotNull(parentTasks);
        Assert.assertEquals(1, parentTasks.size());
        Assert.assertEquals(taskTestObject.getParentTask().getParentTaskName(), parentTasks.get(0).getParentTaskName());
    }

    @Test
    public void saveParentTask() {
        when(parentTaskRepository.save(any(ParentTask.class))).thenReturn(taskTestObject.getParentTask());
        ParentTask savedPrentTask = taskManagerDao.saveParentTask(taskTestObject.getParentTask());

        Assert.assertNotNull(savedPrentTask);
        Assert.assertEquals(taskTestObject.getParentTask().getParentTaskName(), savedPrentTask.getParentTaskName());
    }

    @Test
    public void findAllByProjectId() {
        when(taskRepository.findAllByProjectId(anyLong())).thenReturn(Collections.singletonList(taskTestObject));
        List<Task> tasks = taskManagerDao.findAllByProjectId(taskTestObject.getProject().getProjectId());
        Assert.assertNotNull(tasks);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(taskTestObject.getTaskName(), tasks.get(0).getTaskName());
    }

    @Test
    public void updateTaskDetails() {

        when(taskRepository.updateTaskDetails(anyString(), any(LocalDate.class), any(LocalDate.class), anyInt(), anyLong(), anyLong(), anyLong())).thenReturn(1);
        int number = taskManagerDao.updateTaskDetails(taskTestObject.getTaskName(), taskTestObject.getStartDate(), taskTestObject.getEndDate(), taskTestObject.getPriority(), taskTestObject.getParentTask().getParentId(), taskTestObject.getProject().getProjectId(), taskTestObject.getTaskId());

        assertTrue(number > 0);
    }

    @Test
    public void deleteTasksByIds() {
        doNothing().when(taskRepository).deleteTaksByIds(anyList());
        taskManagerDao.deleteTasksByIds(anyList());
        verify(taskRepository, times(1)).deleteTaksByIds(anyList());
    }
}