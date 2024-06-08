package com.example.emsi.taskmanager.controllers.user;

import com.example.emsi.taskmanager.entities.user.Sex;
import com.example.emsi.taskmanager.entities.user.User;
import com.example.emsi.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Adding a user
    @PostMapping("/add")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("fullName") String fullName,
                          @RequestParam("tel") String tel,
                          @RequestParam("birthDay") LocalDate birthDay,
                          @RequestParam("email") String email,
                          @RequestParam("inscriptionDate") LocalDate inscriptionDate,
                          @RequestParam("sex") Sex sex) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullName);
        user.setTel(tel);
        user.setBirthDay(birthDay);
        user.setEmail(email);
        user.setSex(sex);
        user.setInscriptionDate(inscriptionDate);

        userService.addUser(user);
        return "redirect:/alls";
    }
    @GetMapping("/adduser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "AddUser"; // This should match the name of your HTML file without the .html extension
    }



    @GetMapping("/alls")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        System.out.println("Number of users retrieved: " + users.size());
        return "All.html";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/alls";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
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

    @GetMapping("/search")
    public String searchUserByFullName(@RequestParam("fullName") String fullName, Model model) {
        List<User> users = userService.getUsersByFullName(fullName);
        model.addAttribute("users", users);
        return "SearchResults.html";
    }
}
