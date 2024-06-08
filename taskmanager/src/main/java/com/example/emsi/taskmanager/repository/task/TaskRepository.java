package com.example.emsi.taskmanager.repository.task;

import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.task.TaskStatus;
import com.example.emsi.taskmanager.entities.project.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStartDate(LocalDate startDate);
    List<Task> findByEndDate(LocalDate endDate);
    List<Task> findByTitle(String title);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByProject(Project project);
    List<Task> findByProjectId(Long projectId);
    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.members")
    List<Task> findAllWithMembers();
}
