package org.kajal.mallick.facade;

import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.dao.ProjectDao;
import org.kajal.mallick.entities.Project;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectFacadeImplTest {
    @InjectMocks
    private ProjectFacadeImpl projectFacade;

    @Mock
    private ProjectDao projectDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllProjects() {
        Project project = new Project();
        project.setProjectId(1l);
        when(projectDao.findAllProjects()).thenReturn(Collections.singletonList(project));

        List<Project> projects = projectFacade.findAllProjects();
        assertNotNull(projects);
        assertEquals(1l, projects.get(0).getProjectId());
    }
}