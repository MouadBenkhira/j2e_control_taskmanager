package com.example.emsi.taskmanager.controllers.project;

import com.example.emsi.taskmanager.entities.project.Project;
import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.task.TaskStatus;
import com.example.emsi.taskmanager.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/addproject")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "ProjectAdd";
    }

    @PostMapping("/addproject")
    public String addProject(@ModelAttribute Project project) {
        projectService.addProject(project);
        return "redirect:/projects/showprojects";
    }

    @GetMapping("/showprojects")
    public String showAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "Project";
    }



    @GetMapping("/projecttask/{projectId}")
    public String showAllTasksInProject(@PathVariable Long projectId, Model model) {
        model.addAttribute("tasks", projectService.getAllTasksInProject(projectId));
        return "projecttask";
    }
    @PostMapping("/delete")
    public String deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects/showprojects";
    }

    @PostMapping("/update")
    public String updateProject(@RequestParam("id") Long id,
                                @RequestParam("description") String description,
                                @RequestParam("startDate") LocalDate startDate,
                                @RequestParam("endDate") LocalDate endDate) {

        Optional<Project> optionalProject = projectService.getProjectById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setDescription(description);
            project.setStartDate(startDate);
            project.setEndDate(endDate);

            projectService.updtateProject(project);
        }
        return "redirect:/projects/showprojects";
    }

}
