package com.example.emsi.taskmanager.services.project;

import com.example.emsi.taskmanager.entities.project.Project;
import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.task.TaskStatus;
import com.example.emsi.taskmanager.repository.project.ProjectRepository;
import com.example.emsi.taskmanager.repository.task.TaskRepository;
import com.example.emsi.taskmanager.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> getProjectsByStartDate(LocalDate startDate) {
        return projectRepository.findByStartDate(startDate);
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByEndDate(LocalDate endDate) {
        return projectRepository.findByEndDate(endDate);
    }

    public List<Project> getProjectsByManager(Long managerId) {
        return projectRepository.findByManagerId(managerId);
    }

    public List<Task> getAllTasksInProject(Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    public Task addTaskToProject(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        task.setProject(project);
        return taskRepository.save(task);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updateProjectById(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        // Update the existing project with the new information
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setEndDate(updatedProject.getEndDate());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setManager(updatedProject.getManager());

        return projectRepository.save(existingProject);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updtateProject(Project project) {
        Optional<Project> existingProject = projectRepository.findById(project.getId());
        if (existingProject.isPresent()) {
            return projectRepository.save(project);

        } else {
            throw new RuntimeException("Task not found");
        }
    }




}
