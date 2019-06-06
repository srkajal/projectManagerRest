package org.kajal.mallick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajal.mallick.model.ProjectDto;
import org.kajal.mallick.model.request.ProjectRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.model.response.ProjectResponse;
import org.kajal.mallick.service.ProjectService;
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
@WebMvcTest(value = ProjectController.class)
public class ProjectControllerTest {

    String PATH = "/api/project/";
    String PROJECT_NAME = "Project Name 1";

    private ProjectDto projectDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        objectMapper = new ObjectMapper();

        projectDto = new ProjectDto(1l, PROJECT_NAME, "01-01-2019", "01-02-2019", 1, 2);
    }

    @Test
    public void findAllProjects() throws Exception {
        ProjectListResponse projectListResponse = new ProjectListResponse();
        projectListResponse.setProjects(Collections.singletonList(projectDto));
        projectListResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Projects found"));

        when(projectService.findAllProjects()).thenReturn(projectListResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findAllProjects")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projects", hasSize(1)))
                .andExpect(jsonPath("$.projects[0].project_name", org.hamcrest.Matchers.is(PROJECT_NAME)));

        verify(projectService, times(1)).findAllProjects();
    }

    @Test
    public void findProjectById() throws Exception {
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setProjectDto(projectDto);
        projectResponse.setBaseResponse(new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Projects found"));

        when(projectService.findByProjectId(anyLong())).thenReturn(projectResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "findProjectById/1")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.project.project_name", org.hamcrest.Matchers.is(PROJECT_NAME)));

        verify(projectService, times(1)).findByProjectId(anyLong());
    }

    @Test
    public void createProject() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.CREATED.value());

        String projectRequestString = "{\n" +
                "    \"project_name\": \"Project 1\",\n" +
                "    \"user_id\": 1,\n" +
                "    \"start_date\": \"2019-06-09\",\n" +
                "    \"end_date\": \"2019-07-09\",\n" +
                "    \"priority\": 5\n" +
                "}";

        when(projectService.createProject(any(ProjectRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "createProject")
                .accept(MediaType.APPLICATION_JSON)
                .content(projectRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.CREATED.value())));

        verify(projectService, times(1)).createProject(any(ProjectRequest.class));
    }

    @Test
    public void updateProject() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());

        String projectRequestString = "{\n" +
                "    \"project_id\": 1,\n" +
                "    \"project_name\": \"Project 1\",\n" +
                "    \"user_id\": 1,\n" +
                "    \"start_date\": \"2019-06-09\",\n" +
                "    \"end_date\": \"2019-07-09\",\n" +
                "    \"priority\": 5\n" +
                "}";

        when(projectService.updateProject(any(ProjectRequest.class))).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(PATH + "updateProject")
                .accept(MediaType.APPLICATION_JSON)
                .content(projectRequestString)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(projectService, times(1)).updateProject(any(ProjectRequest.class));
    }

    @Test
    public void deleteById() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());

        when(projectService.deleteProject(anyLong())).thenReturn(baseResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(PATH + "delete/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", org.hamcrest.Matchers.is(HttpStatus.OK.value())));

        verify(projectService, times(1)).deleteProject(anyLong());
    }
}