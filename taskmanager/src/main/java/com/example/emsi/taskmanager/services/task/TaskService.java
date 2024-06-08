package com.example.emsi.taskmanager.services.task;

import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.task.TaskStatus;
import com.example.emsi.taskmanager.entities.project.Project;
import com.example.emsi.taskmanager.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByStartDate(LocalDate startDate) {
        return taskRepository.findByStartDate(startDate);
    }

    public List<Task> getTasksByEndDate(LocalDate endDate) {
        return taskRepository.findByEndDate(endDate);
    }

    public List<Task> getTasksByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }


    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        Optional<Task> existingTask = taskRepository.findById(task.getId());
        if (existingTask.isPresent()) {
            return taskRepository.save(task);

        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found");
        }
    }


    public List<Task> getAllTasksWithMembers() {
        return taskRepository.findAllWithMembers();
    }
    public List<Task> getCompletedTasks() {
        return taskRepository.findByStatus(TaskStatus.DONE);
    }
}
