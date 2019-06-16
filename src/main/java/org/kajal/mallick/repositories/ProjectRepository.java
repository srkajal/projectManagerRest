package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    static final String UPDATE_PROJECT_DETAILS = "update Project p set p.projectName = :projectName, p.startDate = :startDate, p.endDate = :endDate, p.priority = :priority where p.projectId = :projectId";
    static final String UPDATE_PROJECT_STATUS = "update Project p set p.status = :status where p.projectId = :projectId";
    static final String FIND_ALL_PROJECTS_WITH_USER = "SELECT p FROM Project p JOIN FETCH p.user";
    static final String FIND_ALL_ACTIVE_PROJECTS = "SELECT p FROM Project p JOIN FETCH p.user WHERE p.status = :status";
    static final String FIND_PROJECT_BY_PROJECT_ID_WITH_TASKS = "SELECT p FROM Project p LEFT JOIN FETCH p.tasks WHERE p.projectId = :projectId";


    @Query(FIND_ALL_PROJECTS_WITH_USER)
    List<Project> findAllProjectsWithUser();

    @Query(FIND_ALL_ACTIVE_PROJECTS)
    List<Project> findAllByStatus(@Param("status") String status);

    @Query(FIND_PROJECT_BY_PROJECT_ID_WITH_TASKS)
    Optional<Project> findProjectByIdWithTasks(@Param("projectId") long id);

    Project save(Project project);

    @Query(UPDATE_PROJECT_DETAILS)
    @Modifying
    int updateProjectDetails(@Param("projectName") String projectName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("priority") int priority, @Param("projectId") long projectId);

    @Query(UPDATE_PROJECT_STATUS)
    @Modifying
    int updateProjectStatus(@Param("status") String status, @Param("projectId") long projectId);
}