package org.kajal.mallick.facade;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.dao.ProjectDao;
import org.kajal.mallick.dao.TaskDao;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.model.request.ProjectRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectFacadeImplTest {
    private final Project projectTestObject;
    @InjectMocks
    private ProjectFacadeImpl projectFacade;
    @Mock
    private ProjectDao projectDao;
    @Mock
    private TaskDao taskDao;
    @Mock
    private UserDao userDao;

    public ProjectFacadeImplTest(Project projectTestObject) {
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
        when(projectDao.findAllProjects()).thenReturn(Collections.singletonList(projectTestObject));

        List<Project> projects = projectFacade.findAllProjects();
        assertNotNull(projects);
        assertEquals(projectTestObject.getProjectId(), projects.get(0).getProjectId());
    }

    @Test
    public void findByProjectId() {
        when(projectDao.findByProjectId(anyLong())).thenReturn(projectTestObject);

        Project project = projectFacade.findByProjectId(projectTestObject.getProjectId());

        assertEquals(projectTestObject.getProjectId(), project.getProjectId());
    }

    @Test
    public void saveProject() {
        when(projectDao.save(any(Project.class))).thenReturn(projectTestObject);
        when(userDao.findByUserId(anyLong())).thenReturn(new User(projectTestObject.getProjectId() + 1));

        ProjectRequest projectRequest = new ProjectRequest();

        projectRequest.setProjectName(projectTestObject.getProjectName());
        projectRequest.setStartDate(projectTestObject.getStartDate());
        projectRequest.setEndDate(projectTestObject.getEndDate());
        projectRequest.setPriority(projectTestObject.getPriority());
        projectRequest.setUserId(projectTestObject.getProjectId() + 2);

        Project project = projectFacade.saveProject(projectRequest);

        assertEquals(projectTestObject.getProjectId(), project.getProjectId());
    }

    @Test
    public void updateProject() {
        when(projectDao.updateProjectDetails(anyString(), any(LocalDate.class), any(LocalDate.class), anyInt(), anyLong())).thenReturn(1);

        ProjectRequest projectRequest = new ProjectRequest();

        projectRequest.setProjectName(projectTestObject.getProjectName());
        projectRequest.setStartDate(projectTestObject.getStartDate());
        projectRequest.setEndDate(projectTestObject.getEndDate());
        projectRequest.setPriority(projectTestObject.getPriority());
        projectRequest.setUserId(projectTestObject.getProjectId() + 2);
        projectRequest.setProjectId(projectTestObject.getProjectId());

        int number = projectFacade.updateProject(projectRequest);

        assertTrue(number > 0);
    }

    @Test
    public void deleteProject() {
        doNothing().when(projectDao).deleteProject(anyLong());
        when(taskDao.findAllByProjectId(anyLong())).thenReturn(projectTestObject.getTasks());
        when(userDao.removeTasksFromUser(anyList())).thenReturn(1);
        doNothing().when(taskDao).deleteTasksByIds(anyList());
        when(userDao.removeProjectFromUser(anyLong())).thenReturn(1);

        projectFacade.deleteProject(projectTestObject.getProjectId());

        verify(projectDao, times(1)).deleteProject(anyLong());
        verify(userDao, times(1)).removeProjectFromUser(anyLong());
    }
}