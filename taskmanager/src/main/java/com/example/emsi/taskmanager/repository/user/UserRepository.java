package com.example.emsi.taskmanager.repository.user;

import com.example.emsi.taskmanager.entities.user.Sex;
import com.example.emsi.taskmanager.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySex(Sex sex);
    List<User> findByInscriptionDate(LocalDate inscriptionDate);
    List<User> findByBirthDay(LocalDate birthDay);
    List<User> findByFullname(String fullName);

    User findByUsernameAndPassword(String username, String password);
}
