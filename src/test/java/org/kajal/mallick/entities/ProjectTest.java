package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectTest {
    @InjectMocks
    private Project project;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getProjectId() {
        project.setProjectId(1l);

        assertEquals(1l, project.getProjectId());
    }

    @Test
    public void getProjectName() {
        project.setProjectName("project Name");
        assertEquals("project Name", project.getProjectName());
    }

    @Test
    public void getStartDate() {
        project.setStartDate(LocalDate.ofYearDay(2019, 12));

        assertEquals(2019, project.getStartDate().getYear());
    }

    @Test
    public void getEndDate() {
        project.setEndDate(LocalDate.ofYearDay(2019, 12));

        assertEquals(2019, project.getEndDate().getYear());
    }

    @Test
    public void getPriority() {
        project.setPriority(1);
        assertEquals(1, project.getPriority());
    }

    @Test
    public void equals1() {
        Project project1 = new Project();
        Project project2 = new Project();

        project1.setProjectId(1l);
        project1.setProjectName("Project Name");
        project1.setStartDate(LocalDate.ofYearDay(2019, 12));
        project1.setEndDate(LocalDate.ofYearDay(2019, 12));
        project1.setPriority(1);

        project2.setProjectId(1l);
        project2.setProjectName("Project Name");
        project2.setStartDate(LocalDate.ofYearDay(2019, 12));
        project2.setEndDate(LocalDate.ofYearDay(2019, 12));
        project2.setPriority(1);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void hashCode1() {
        assertTrue(project.hashCode() > 0);
    }

    @Test
    public void toString1() {
        assertNotNull(project.toString());
    }
}