package com.example.emsi.taskmanager.repository.project;

import com.example.emsi.taskmanager.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStartDate(LocalDate startDate);
    List<Project> findByEndDate(LocalDate endDate);
    List<Project> findByManagerId(Long managerId);
}
