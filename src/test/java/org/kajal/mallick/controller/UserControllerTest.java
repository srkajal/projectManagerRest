package org.kajal.mallick.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajal.mallick.model.ProjectDto;
import org.kajal.mallick.model.TaskDto;
import org.kajal.mallick.model.UserDto;
import org.kajal.mallick.model.request.UserRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.UserListResponse;
import org.kajal.mallick.model.response.UserResponse;
import org.kajal.mallick.service.UserService;
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
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    String PATH = "/api/user/";
    String FIRST_NAME = "Dhiman";

    private UserDto userDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    //private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        userDto = new UserDto(1l, "Dhiman", "Roy", 1, new ProjectDto(), new TaskDto());
    }

    @Test
    public void findAllUsers() throws Exception {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(Collections.singletonList(userDto));
        userListResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Users found"));

        when(userService.findAllUsers()).thenReturn(userListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findAllUsers")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.users", hasSize(1)))
                .andExpect(jsonPath("$.users[0].first_name", org.hamcrest.Matchers.is(FIRST_NAME)));

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    public void findUsersWithNoProject() throws Exception {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(Collections.singletonList(userDto));
        userListResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Users found"));

        when(userService.findUsersWithNoProject()).thenReturn(userListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findUsersWithNoProject")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.users", hasSize(1)))
                .andExpect(jsonPath("$.users[0].first_name", org.hamcrest.Matchers.is(FIRST_NAME)));

        verify(userService, times(1)).findUsersWithNoProject();
    }

    @Test
    public void findUsersWithNoTask() throws Exception {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(Collections.singletonList(userDto));
        userListResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Users found"));

        when(userService.findUsersWithNoTask()).thenReturn(userListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findUsersWithNoTask")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.users", hasSize(1)))
                .andExpect(jsonPath("$.users[0].first_name", org.hamcrest.Matchers.is(FIRST_NAME)));

        verify(userService, times(1)).findUsersWithNoTask();
    }

    @Test
    public void findUserById() throws Exception {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserDto(userDto);
        userResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Users found"));

        when(userService.findByUserId(anyLong())).thenReturn(userResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findUserById/1")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.first_name", org.hamcrest.Matchers.is(FIRST_NAME)));

        verify(userService, times(1)).findByUserId(anyLong());
    }

    @Test
    public void createUser() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.CREATED.value());
        when(userService.createUser(any(UserRequest.class))).thenReturn(baseResponse);

        String userRequestString = "{\n" +
                "    \"task_id\": 1,\n" +
                "    \"project_id\": 1,\n" +
                "    \"user_id\": 1,\n" +
                "    \"first_name\": \"Dhiman\",\n" +
                "    \"last_name\": \"Roy\",\n" +
                "    \"employee_id\": 5\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "createUser")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequestString)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.CREATED.value())));

        verify(userService, times(1)).createUser(any(UserRequest.class));
    }

    @Test
    public void updateUser() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        when(userService.updateUserDetails(any(UserRequest.class))).thenReturn(baseResponse);

        String userRequestString = "{\n" +
                "    \"task_id\": 1,\n" +
                "    \"project_id\": 1,\n" +
                "    \"user_id\": 1,\n" +
                "    \"first_name\": \"Dhiman\",\n" +
                "    \"last_name\": \"Roy\",\n" +
                "    \"employee_id\": 5\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "updateUser")
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequestString)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(userService, times(1)).updateUserDetails(any(UserRequest.class));
    }

    @Test
    public void removeUserById() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        when(userService.removeUserById(anyLong())).thenReturn(baseResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "delete/1")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(userService, times(1)).removeUserById(anyLong());
    }
}