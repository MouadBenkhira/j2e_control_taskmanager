package com.example.emsi.taskmanager.controllers.manager;

import com.example.emsi.taskmanager.entities.manager.Manager;

import com.example.emsi.taskmanager.entities.user.Sex;
import com.example.emsi.taskmanager.entities.user.User;
import com.example.emsi.taskmanager.services.manager.ManagerService;
import com.example.emsi.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;
    private final UserService userService;

    @Autowired
    public ManagerController(ManagerService managerService, UserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
        Manager savedManager = managerService.addManager(manager);
        return new ResponseEntity<>(savedManager, HttpStatus.CREATED);
    }

    @PostMapping("/deletem")
    public String deleteUserM(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/alls";
    }

    @PostMapping("/updatem")
    public String updateUserM(@RequestParam("id") Long id,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("tel") String tel,
                             @RequestParam("birthDay") LocalDate birthDay,
                             @RequestParam("email") String email,
                             @RequestParam("inscriptionDate") LocalDate inscriptionDate,
                             @RequestParam("sex") Sex sex) {

        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(username);
            user.setPassword(password);
            user.setFullname(fullName);
            user.setTel(tel);
            user.setBirthDay(birthDay);
            user.setEmail(email);
            user.setSex(sex);
            user.setInscriptionDate(inscriptionDate);

            userService.updateUser(user);
        }
        return "redirect:/alls";
    }
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        return "admindashboard"; // This should match the name of your HTML file without the .html extension
    }

}
