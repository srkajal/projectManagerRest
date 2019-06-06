package org.kajal.mallick.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.repositories.ProjectRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectDaoImplTest {

    private final Project project2;
    @InjectMocks
    private ProjectDaoImpl projectDao;
    @Mock
    private ProjectRepository projectRepository;

    public ProjectDaoImplTest(Project project2) {
        this.project2 = project2;
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
        when(projectRepository.findAll()).thenReturn(Collections.singletonList(project2));

        List<Project> projects = projectDao.findAllProjects();
        assertNotNull(projects);
        assertEquals(project2.getProjectId(), projects.get(0).getProjectId());
    }

    @Test
    public void findByProjectId() {
        when(projectRepository.findProjectByIdWithTasks(anyLong())).thenReturn(Optional.of(project2));

        Project project = projectDao.findByProjectId(1l);

        assertEquals(project2.getProjectId(), project.getProjectId());
    }

    @Test
    public void save() {
        when(projectRepository.save(any(Project.class))).thenReturn(project2);
        Project project = projectDao.save(project2);
        assertEquals(project2.getProjectId(), project.getProjectId());
    }

    @Test
    public void updateProjectDetails() {
        when(projectRepository.updateProjectDetails(anyString(), any(LocalDate.class), any(LocalDate.class), anyInt(), anyLong())).thenReturn(1);
        int number = projectDao.updateProjectDetails(project2.getProjectName(), project2.getStartDate(), project2.getEndDate(), project2.getPriority(), project2.getProjectId());
        assertTrue(number > 0);
    }

    @Test
    public void deleteProject() {
        doNothing().when(projectRepository).deleteById(anyLong());
        projectDao.deleteProject(project2.getProjectId());

        verify(projectRepository, times(1)).deleteById(anyLong());
    }
}