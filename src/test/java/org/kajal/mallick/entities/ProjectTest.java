package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectTest {
    private final Project project2;
    @InjectMocks
    private Project project;

    public ProjectTest(Project project2) {
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
    public void getProjectId() {
        project.setProjectId(project2.getProjectId());

        assertEquals(project2.getProjectId(), project.getProjectId());
    }

    @Test
    public void getProjectName() {
        project.setProjectName(project2.getProjectName());
        assertEquals(project2.getProjectName(), project.getProjectName());
    }

    @Test
    public void getStartDate() {
        project.setStartDate(project2.getStartDate());

        assertEquals(project2.getStartDate().getDayOfYear(), project.getStartDate().getDayOfYear());
    }

    @Test
    public void getEndDate() {
        project.setEndDate(project2.getEndDate());

        assertEquals(project2.getEndDate().getDayOfYear(), project.getEndDate().getDayOfYear());
    }

    @Test
    public void getPriority() {
        project.setPriority(project2.getPriority());
        assertEquals(project2.getPriority(), project.getPriority());
    }

    @Test
    public void equals1() {
        Project project1 = new Project();
        Project project3 = new Project();

        project1.setProjectId(project2.getProjectId());
        project1.setProjectName(project2.getProjectName());
        project1.setStartDate(project2.getStartDate());
        project1.setEndDate(project2.getEndDate());
        project1.setPriority(project2.getPriority());

        project3.setProjectId(project2.getProjectId());
        project3.setProjectName(project2.getProjectName());
        project3.setStartDate(project2.getStartDate());
        project3.setEndDate(project2.getEndDate());
        project3.setPriority(project2.getPriority());

        assertTrue(project1.equals(project3));

        assertTrue(project1.equals(project1));
        assertFalse(project1.equals(null));
        assertFalse(project1.equals(new Object()));
        assertTrue(project1.equals(project3));

        project3.setEndDate(LocalDate.ofYearDay(2019, 16));
        assertFalse(project1.equals(project3));

        project3.setStartDate(LocalDate.ofYearDay(2019, 13));
        assertFalse(project1.equals(project3));

        project3.setProjectName(project2.getProjectName() + 1);
        assertFalse(project1.equals(project3));

        project3.setPriority(project2.getPriority() + 1);
        assertFalse(project1.equals(project3));

        project3.setProjectId(project2.getProjectId() + 1);
        assertFalse(project1.equals(project3));
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