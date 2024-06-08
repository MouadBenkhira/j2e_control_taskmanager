package com.example.emsi.taskmanager.services.user;

import com.example.emsi.taskmanager.entities.user.Role;
import com.example.emsi.taskmanager.entities.user.User;
import com.example.emsi.taskmanager.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            if (user.getRole() == Role.MANAGER) {
                return "redirect:/admin";
            } else {
                return "redirect:/member";
            }
        } else {
            return "redirect:/login?error";
        }
    }
}
