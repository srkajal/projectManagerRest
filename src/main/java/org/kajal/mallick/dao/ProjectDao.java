package org.kajal.mallick.dao;

import org.kajal.mallick.entities.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> findAllProjects();
}
