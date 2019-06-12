package org.kajal.mallick;

import org.junit.Assert;
import org.junit.Test;
import org.kajal.mallick.entities.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class BaseDataTest {
    private static final String separator = ",";

    public static Iterable<Object[]> taskData = getTaskData("task.csv", 2);
    public static Iterable<Object[]> projectData = getProjectData("project.csv", 2);
    public static Iterable<Object[]> userData = getUserData("user.csv", 2);

    private static Iterable<Object[]> getTaskData(String csvFile, int noOfObjects) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ProjectTest.class.getClassLoader().getResourceAsStream(csvFile), "UTF-8"))) {

            String newLine = "";

            Object[][] objects = new Object[noOfObjects][1];

            int lineNumber = 0;

            while ((newLine = bufferedReader.readLine()) != null) {
                String[] fieldArray = newLine.split(separator);

                Task task1 = new Task();
                task1.setTaskId(lineNumber + 1);
                task1.setStatus("OPEN");
                Project project = new Project(lineNumber + 2, "Project-" + (lineNumber + 6), LocalDate.now(), LocalDate.now(), lineNumber + 3);
                project.setTasks(Collections.singletonList(task1));
                ParentTask parentTask1 = new ParentTask(lineNumber + 4);
                parentTask1.setParentTaskName("PartentTask - " + (lineNumber + 5));

                objects[lineNumber][0] = new Task(Long.valueOf(fieldArray[0]), parentTask1, project, fieldArray[1], LocalDate.parse(fieldArray[2]), LocalDate.parse(fieldArray[3]), Integer.valueOf(fieldArray[4]), fieldArray[5]);
                lineNumber++;
            }

            return Arrays.asList(objects);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private static Iterable<Object[]> getProjectData(String csvFile, int noOfObjects) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ProjectTest.class.getClassLoader().getResourceAsStream(csvFile), "UTF-8"))) {

            String newLine = "";

            Object[][] objects = new Object[noOfObjects][1];

            int lineNumber = 0;

            while ((newLine = bufferedReader.readLine()) != null) {
                String[] fieldArray = newLine.split(separator);

                Task task1 = new Task();
                task1.setTaskId(lineNumber + 1);
                task1.setStatus("OPEN");

                Project project = new Project(Long.valueOf(fieldArray[0]), fieldArray[1], LocalDate.parse(fieldArray[2]), LocalDate.parse(fieldArray[3]), Integer.valueOf(fieldArray[4]));
                project.setTasks(Collections.singletonList(task1));

                objects[lineNumber][0] = project;
                lineNumber++;
            }

            return Arrays.asList(objects);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private static Iterable<Object[]> getUserData(String csvFile, int noOfObjects) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ProjectTest.class.getClassLoader().getResourceAsStream(csvFile), "UTF-8"))) {

            String newLine = "";

            Object[][] objects = new Object[noOfObjects][1];

            int lineNumber = 0;

            while ((newLine = bufferedReader.readLine()) != null) {
                String[] fieldArray = newLine.split(separator);

                ParentTask parentTask1 = new ParentTask(lineNumber + 4);
                parentTask1.setParentTaskName("ParentTask - " + (lineNumber + 5));
                Task task1 = new Task();
                task1.setTaskId(lineNumber + 1);
                task1.setStatus("OPEN");
                Project project = new Project(lineNumber + 2, "Project-" + (lineNumber + 4), LocalDate.now(), LocalDate.now(), lineNumber + 3);
                project.setTasks(Collections.singletonList(task1));

                Project project2 = new Project(lineNumber + 5, "Project-" + (lineNumber + 6), LocalDate.now(), LocalDate.now(), lineNumber + 7);
                User user1 = new User(lineNumber + 8);
                task1.setProject(project2);
                task1.setUser(user1);

                objects[lineNumber][0] = new User(Long.valueOf(fieldArray[0]), fieldArray[1], fieldArray[2], Integer.valueOf(fieldArray[3]), project, task1);
                lineNumber++;
            }

            return Arrays.asList(objects);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Test
    public void mockTest() {
        Assert.assertTrue(true);
    }
}
