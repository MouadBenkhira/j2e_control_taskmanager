package com.example.emsi.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        return "admindashboard"; // This should match the name of your HTML file without the .html extension
    }
}
