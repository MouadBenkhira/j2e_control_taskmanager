package com.example.emsi.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/member")
    public String adminDashboard(Model model) {
        return "member"; // This should match the name of your HTML file without the .html extension
    }
}
