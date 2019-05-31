package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskManagerRepository extends JpaRepository<Task, Long> {
    final static String UPDATE_TASK_STATUS = "update Task t set t.status = :status where t.taskId = :taskId";

    List<Task> findAll();

    Optional<Task> findById(long id);

    Task save(Task task);

    @Modifying
    @Query(UPDATE_TASK_STATUS)
    int updateTaskStatus(@Param("status") String status, @Param("taskId") long taskId);
}
