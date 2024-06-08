package com.example.emsi.taskmanager.services.user;

import com.example.emsi.taskmanager.entities.user.Role;
import com.example.emsi.taskmanager.entities.user.Sex;
import com.example.emsi.taskmanager.entities.user.User;
import com.example.emsi.taskmanager.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersBySex(Sex sex) {
        return userRepository.findBySex(sex);
    }

    public List<User> getUsersByInscriptionDate(LocalDate inscriptionDate) {
        return userRepository.findByInscriptionDate(inscriptionDate);
    }

    public List<User> getUsersByBirthday(LocalDate birthDay) {
        return userRepository.findByBirthDay(birthDay);
    }

    public List<User> getUsersByFullName(String fullName) {
        return userRepository.findByFullname(fullName);
    }

    public User addUser(User user) {
        user.setRole(Role.MEMBER);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            return userRepository.save(user);

        } else {
            throw new RuntimeException("User not found");
        }
    }

}
