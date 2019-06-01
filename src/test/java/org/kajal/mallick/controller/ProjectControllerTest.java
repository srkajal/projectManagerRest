package org.kajal.mallick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kajal.mallick.model.ProjectDto;
import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
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
@WebMvcTest(value = ProjectControllerTest.class)
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

        projectDto = new ProjectDto(1l, PROJECT_NAME, "01-01-2019", "01-02-2019", 1);
    }

    @Test
    public void findAllProjects() throws Exception {
        ProjectListResponse projectListResponse = new ProjectListResponse();
        projectListResponse.setProjects(Collections.singletonList(projectDto));

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
}