package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentTaskManagerRepository extends JpaRepository<ParentTask, Long> {

    List<ParentTask> findAll();

    ParentTask save(ParentTask parentTask);
}
