package org.kajal.mallick.model;

import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectDtoTest {

    @InjectMocks
    private ProjectDto projectDto;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getProjectId() {
        projectDto.setProjectId(1l);

        assertEquals(1l, projectDto.getProjectId());
    }

    @Test
    public void getProjectName() {
        projectDto.setProjectName("project1");

        assertEquals("project1", projectDto.getProjectName());
    }

    @Test
    public void getStartDate() {

        projectDto.setStartDate("01-01-2019");

        assertEquals("01-01-2019", projectDto.getStartDate());
    }

    @Test
    public void getEndDate() {
        projectDto.setEndDate("01-02-2019");

        assertEquals("01-02-2019", projectDto.getEndDate());
    }

    @Test
    public void getPriority() {
        projectDto.setPriority(3);
        assertEquals(3, projectDto.getPriority());
    }

    @Test
    public void projectDtoByProject() {
        Project project1 = new Project();
        Task task1 = new Task();
        task1.setStatus("OPEN");

        project1.setProjectId(1l);
        project1.setProjectName("Project Name");
        project1.setStartDate(LocalDate.ofYearDay(2019, 12));
        project1.setEndDate(LocalDate.ofYearDay(2019, 12));
        project1.setPriority(1);
        project1.setTasks(Collections.singletonList(task1));

        ProjectDto projectDto1 = new ProjectDto(project1, false);

        assertEquals("Project Name", projectDto1.getProjectName());
    }
}