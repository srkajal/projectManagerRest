package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAll();

    Project save(Project project);
}
