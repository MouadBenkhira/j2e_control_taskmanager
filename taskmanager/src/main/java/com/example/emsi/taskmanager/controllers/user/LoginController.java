package com.example.emsi.taskmanager.controllers.user;

import com.example.emsi.taskmanager.services.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "Login"; // This should be the name of your login HTML file
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        return loginService.login(username, password);
    }
}
