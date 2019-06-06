package org.kajal.mallick.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.facade.ProjectFacade;
import org.kajal.mallick.model.request.ProjectRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectServiceImplTest {

    private final Project projectTestObject;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectFacade projectFacade;

    public ProjectServiceImplTest(Project projectTestObject) {
        this.projectTestObject = projectTestObject;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with PROJECT={0}")
    public static Iterable<Object[]> data() {
        return BaseDataTest.projectData;
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllProjects() {
        when(projectFacade.findAllProjects()).thenReturn(Collections.singletonList(projectTestObject));

        assertEquals(projectTestObject.getProjectId(), projectService.findAllProjects().getProjects().get(0).getProjectId());

    }

    @Test
    public void findByProjectId() {

        when(projectFacade.findByProjectId(anyLong())).thenReturn(projectTestObject);

        assertEquals(projectTestObject.getProjectId(), projectService.findByProjectId(projectTestObject.getProjectId()).getProjectDto().getProjectId());
    }

    @Test
    public void createProject() {
        when(projectFacade.saveProject(any(ProjectRequest.class))).thenReturn(projectTestObject);

        ProjectRequest projectRequest = new ProjectRequest();

        projectRequest.setProjectName(projectTestObject.getProjectName());
        projectRequest.setStartDate(projectTestObject.getStartDate());
        projectRequest.setEndDate(projectTestObject.getEndDate());
        projectRequest.setPriority(projectTestObject.getPriority());
        projectRequest.setUserId(projectTestObject.getProjectId() + 2);

        assertEquals(HttpStatus.CREATED.value(), projectService.createProject(projectRequest).getCode().intValue());
    }

    @Test
    public void updateProject() {
        when(projectFacade.updateProject(any(ProjectRequest.class))).thenReturn(1);

        ProjectRequest projectRequest = new ProjectRequest();

        projectRequest.setProjectName(projectTestObject.getProjectName());
        projectRequest.setStartDate(projectTestObject.getStartDate());
        projectRequest.setEndDate(projectTestObject.getEndDate());
        projectRequest.setPriority(projectTestObject.getPriority());
        projectRequest.setUserId(projectTestObject.getProjectId() + 2);
        projectRequest.setProjectId(projectTestObject.getProjectId());

        assertEquals(HttpStatus.OK.value(), projectService.updateProject(projectRequest).getCode().intValue());
    }

    @Test
    public void deleteProject() {
        doNothing().when(projectFacade).deleteProject(anyLong());

        assertEquals(HttpStatus.OK.value(), projectService.deleteProject(projectTestObject.getProjectId()).getCode().intValue());

        verify(projectFacade, times(1)).deleteProject(anyLong());
    }
}