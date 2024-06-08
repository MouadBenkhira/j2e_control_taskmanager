package com.example.emsi.taskmanager.controllers.tastk;

import com.example.emsi.taskmanager.entities.project.Project;
import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.task.TaskStatus;
import com.example.emsi.taskmanager.entities.user.Sex;
import com.example.emsi.taskmanager.entities.user.User;
import com.example.emsi.taskmanager.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "Task";
    }


    @GetMapping("/addtask")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "Addtask";
    }

    @PostMapping("/addtask")
    public String addTask(@ModelAttribute Task task, @RequestParam Long userId, @RequestParam Long projectId) {
        User user = new User();
        user.setId(userId);
        task.setUser(user);

        Project project = new Project();
        project.setId(projectId);
        task.setProject(project);

        taskService.addTask(task);
        return "redirect:/task/all";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/startDate")
    public ResponseEntity<List<Task>> getTasksByStartDate(@RequestParam LocalDate startDate) {
        List<Task> tasks = taskService.getTasksByStartDate(startDate);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/endDate")
    public ResponseEntity<List<Task>> getTasksByEndDate(@RequestParam LocalDate endDate) {
        List<Task> tasks = taskService.getTasksByEndDate(endDate);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Task>> getTasksByTitle(@RequestParam String title) {
        List<Task> tasks = taskService.getTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/project")
    public ResponseEntity<List<Task>> getTasksByProject(@RequestParam Project project) {
        List<Task> tasks = taskService.getTasksByProject(project);
        return ResponseEntity.ok(tasks);
    }



    @GetMapping("/project/{projectId}")
    public String getTasksByProjectId(@PathVariable Long projectId, Model model) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        model.addAttribute("tasks", tasks);
        return "projecttask";
    }


    @PostMapping("/delete")
    public String deletTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/task/all";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("Title") String title,
                             @RequestParam("Description") String description,
                             @RequestParam("StartDate") LocalDate startdate,
                             @RequestParam("End Date") LocalDate enddate,
                             @RequestParam("Status") TaskStatus status,
                             @RequestParam("Priority") int priority){

        Optional<Task> optionalTask = taskService.getTaskById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(title);
            task.setDescription(description);
            task.setStartDate(startdate);
            task.setEndDate(enddate);
            task.setStatus(status);
            task.setPriority(priority);


            taskService.updateTask(task);
        }
        return "redirect:/task/all";
    }

    @PostMapping("/updatestatus")
    public String updateTaskStatus(@RequestParam("id") Long id,
                                   @RequestParam("status") TaskStatus status,
                                   Model model) {
        taskService.updateTaskStatus(id, status);
        return "redirect:/task/membertaskshowall";
    }


    @GetMapping("/membertaskshowall")
    public String showAllMemberTasks(Model model) {
        List<Task> tasks = taskService.getAllTasksWithMembers();
        model.addAttribute("tasks", tasks);
        return "memberdashboard"; // Assuming your HTML file is named membertask.html
    }

    @GetMapping("/archive")
    public String showCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks();
        model.addAttribute("completedTasks", completedTasks);
        return "archive"; // Assuming you have a Thymeleaf template named archive.html
    }

}
