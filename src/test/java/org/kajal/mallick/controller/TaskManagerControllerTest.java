package org.kajal.mallick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajal.mallick.model.ParentTaskDto;
import org.kajal.mallick.model.TaskDto;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ExtendedParentTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskResponse;
import org.kajal.mallick.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskManagerController.class)
public class TaskManagerControllerTest {

    String PATH = "/api/task/";
    String TASK_NAME = "Task Name 1";

    private TaskDto taskDto;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskManagerService taskManagerService;
    private ParentTaskDto parentTaskDto;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        initMocks(this);
        taskDto = new TaskDto();
        taskDto.setTask(TASK_NAME);

        parentTaskDto = new ParentTaskDto(1, TASK_NAME);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void findAllTasks() throws Exception {

        ExtendedTaskListResponse extendedTaskListResponse = new ExtendedTaskListResponse();
        extendedTaskListResponse.setTasks(Collections.singletonList(taskDto));

        when(taskManagerService.findAllTasks()).thenReturn(extendedTaskListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findAllTasks")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tasks", hasSize(1)))
                .andExpect(jsonPath("$.tasks[0].task_name", org.hamcrest.Matchers.is(TASK_NAME)));

        verify(taskManagerService, times(1)).findAllTasks();
    }

    @Test
    public void findAllParentTasks() throws Exception {
        ExtendedParentTaskListResponse extendedParentTaskListResponse = new ExtendedParentTaskListResponse();
        extendedParentTaskListResponse.setParentTasks(Collections.singletonList(parentTaskDto));

        when(taskManagerService.findAllParentTasks()).thenReturn(extendedParentTaskListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findAllParentTasks")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.parent_tasks", hasSize(1)))
                .andExpect(jsonPath("$.parent_tasks[0].parent_task_name", org.hamcrest.Matchers.is(TASK_NAME)));

        verify(taskManagerService, times(1)).findAllParentTasks();
    }

    @Test
    public void findTaskById() throws Exception {
        ExtendedTaskResponse extendedTaskResponse = new ExtendedTaskResponse();
        extendedTaskResponse.setTaskDto(taskDto);
        when(taskManagerService.findTaskById(anyLong())).thenReturn(extendedTaskResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findTaskById/123")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task", org.hamcrest.Matchers.notNullValue()))
                .andExpect(jsonPath("$.task.task_name", org.hamcrest.Matchers.is(TASK_NAME)));

        verify(taskManagerService, times(1)).findTaskById(123l);
    }

    @Test
    public void closeTaskById() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        when(taskManagerService.closeTaskById(anyLong())).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "closeTaskById/123")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(taskManagerService, times(1)).closeTaskById(123l);

    }

    @Test
    public void createTask() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.CREATED.value());

        String taskRequestString = "{\n" +
                "    \"parent_id\": 1,\n" +
                "    \"task_name\": \"Task 1\",\n" +
                "    \"start_date\": \"2019-06-09\",\n" +
                "    \"end_date\": \"2019-07-09\",\n" +
                "    \"priority\": 5\n" +
                "}";

        when(taskManagerService.saveTask(any(TaskRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "createTask")
                .accept(MediaType.APPLICATION_JSON)
                .content(taskRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.CREATED.value())));

        verify(taskManagerService, times(1)).saveTask(any(TaskRequest.class));
    }

    @Test
    public void createTaskBadRequest() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.CREATED.value());

        String taskRequestString = "{\n" +
                "    \"parent_id\": 1,\n" +
                "    \"task_name\": \"\",\n" +
                "    \"start_date\": \"2019-06-09\",\n" +
                "    \"end_date\": \"2019-07-09\",\n" +
                "    \"priority\": 5\n" +
                "}";

        when(taskManagerService.saveTask(any(TaskRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "createTask")
                .accept(MediaType.APPLICATION_JSON)
                .content(taskRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createParentTask() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.CREATED.value());

        String parentTaskRequestString = "{\n" +
                "    \"parent_task_name\": \"AIM\"\n" +
                "}";

        when(taskManagerService.saveParentTask(any(ParentTaskRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "createParentTask")
                .accept(MediaType.APPLICATION_JSON)
                .content(parentTaskRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.CREATED.value())));

        verify(taskManagerService, times(1)).saveParentTask(any(ParentTaskRequest.class));

    }

    @Test
    public void updateTask() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());

        String taskRequestString = "{\n" +
                "    \"task_id\": 1,\n" +
                "    \"parent_id\": 3,\n" +
                "    \"task_name\": \"DataBase Issue\",\n" +
                "    \"start_date\": \"2019-04-27\",\n" +
                "    \"end_date\": \"2019-04-29\",\n" +
                "    \"priority\": 1\n" +
                "}";

        when(taskManagerService.update(any(TaskRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "updateTask")
                .accept(MediaType.APPLICATION_JSON)
                .content(taskRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(taskManagerService, times(1)).update(any(TaskRequest.class));
    }
}