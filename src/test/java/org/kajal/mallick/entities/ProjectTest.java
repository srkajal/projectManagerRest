package org.kajal.mallick.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectTest {
    @InjectMocks
    private Project project;

    private final long projectId;
    private final String projectName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int priority;

    public ProjectTest(long projectId, String projectName, LocalDate startDate, LocalDate endDate, int priority) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with ID={0}, NAME={1}, START_DTAE={2}, END_DATE={3}, PRIORITY={4}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1l, "Project Name - 1", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 15), 1},
                {2l, "Project Name - 2", LocalDate.ofYearDay(2019, 12), LocalDate.ofYearDay(2019, 15), 2},
        });
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getProjectId() {
        project.setProjectId(projectId);

        assertEquals(projectId, project.getProjectId());
    }

    @Test
    public void getProjectName() {
        project.setProjectName(projectName);
        assertEquals(projectName, project.getProjectName());
    }

    @Test
    public void getStartDate() {
        project.setStartDate(startDate);

        assertEquals(startDate.getDayOfYear(), project.getStartDate().getDayOfYear());
    }

    @Test
    public void getEndDate() {
        project.setEndDate(endDate);

        assertEquals(endDate.getDayOfYear(), project.getEndDate().getDayOfYear());
    }

    @Test
    public void getPriority() {
        project.setPriority(priority);
        assertEquals(priority, project.getPriority());
    }

    @Test
    public void equals1() {
        Project project1 = new Project();
        Project project2 = new Project();

        project1.setProjectId(projectId);
        project1.setProjectName(projectName);
        project1.setStartDate(startDate);
        project1.setEndDate(endDate);
        project1.setPriority(priority);

        project2.setProjectId(projectId);
        project2.setProjectName(projectName);
        project2.setStartDate(startDate);
        project2.setEndDate(endDate);
        project2.setPriority(priority);

        assertTrue(project1.equals(project2));

        assertTrue(project1.equals(project1));
        assertFalse(project1.equals(null));
        assertFalse(project1.equals(new Object()));
        assertTrue(project1.equals(project2));

        project2.setEndDate(LocalDate.ofYearDay(2019, 16));
        assertFalse(project1.equals(project2));

        project2.setStartDate(LocalDate.ofYearDay(2019, 13));
        assertFalse(project1.equals(project2));

        project2.setProjectName(projectName + 1);
        assertFalse(project1.equals(project2));

        project2.setPriority(priority + 1);
        assertFalse(project1.equals(project2));

        project2.setProjectId(projectId + 1);
        assertFalse(project1.equals(project2));
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