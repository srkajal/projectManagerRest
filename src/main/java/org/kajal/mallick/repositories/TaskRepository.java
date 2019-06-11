package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    static final String FIND_ALL_TASKS = "SELECT t FROM Task t LEFT JOIN FETCH t.parentTask";
    static final String FIND_TASK_TASK_ID = "SELECT t FROM Task t LEFT JOIN FETCH t.parentTask JOIN t.project p JOIN t.user u WHERE t.taskId = :taskId";
    static final String FIND_ALL_TASK_BY_PROJECT_ID = "SELECT t FROM Task t LEFT JOIN FETCH t.parentTask JOIN t.project p WHERE p.projectId = :projectId";
    static final String UPDATE_TASK_STATUS = "update Task t set t.status = :status where t.taskId = :taskId";
    static final String UPDATE_TASK_DETAILS = "update Task t set t.taskName = :taskName, t.startDate = :startDate, t.endDate = :endDate, t.priority = :priority, t.parentTask.parentId = :parentId, t.project.projectId = :projectId where t.taskId = :taskId";
    static final String DELETE_TASKS_BY_IDS = "DELETE FROM Task t WHERE t.taskId IN :taskIds";

    @Query(FIND_ALL_TASKS)
    List<Task> findAllTasks();

    @Query(FIND_ALL_TASK_BY_PROJECT_ID)
    List<Task> findAllByProjectId(@Param("projectId") long projectId);

    @Query(FIND_TASK_TASK_ID)
    Optional<Task> findByTaskId(@Param("taskId") long taskId);

    Task save(Task task);

    @Modifying
    @Query(UPDATE_TASK_STATUS)
    int updateTaskStatus(@Param("status") String status, @Param("taskId") long taskId);

    @Modifying
    @Query(UPDATE_TASK_DETAILS)
    int updateTaskDetails(@Param("taskName") String taskName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("priority") int priority, @Param("parentId") long parentId, @Param("projectId") long projectId, @Param("taskId") long taskId);

    @Modifying
    @Query(DELETE_TASKS_BY_IDS)
    void deleteTaksByIds(@Param("taskIds") List<Long> taskIds);
}
