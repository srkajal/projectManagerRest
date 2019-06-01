package org.kajal.mallick.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.facade.ProjectFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectServiceImplTest {

    private final List<Project> projects;
    private final int statusValue;
    @InjectMocks
    private ProjectServiceImpl projectService;
    @Mock
    private ProjectFacade projectFacade;

    public ProjectServiceImplTest(List<Project> projects, int statusValue) {
        this.projects = projects;
        this.statusValue = statusValue;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with PROJECT_LIST={0}, EXPECTED={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Collections.singletonList(new Project(1l, "project -1", LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 3), 1)), HttpStatus.FOUND.value()},
                {Collections.EMPTY_LIST, HttpStatus.NOT_FOUND.value()}
        });
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllProjects() {
        when(projectFacade.findAllProjects()).thenReturn(projects);

        assertEquals(statusValue, projectService.findAllProjects().getBaseResponse().getCode().intValue());

    }
}