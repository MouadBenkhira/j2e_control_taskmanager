package com.example.emsi.taskmanager.entities.user;

import com.example.emsi.taskmanager.entities.notification.Notification;
import com.example.emsi.taskmanager.entities.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String tel;
    private LocalDate birthDay;
    private String email;
    private LocalDate inscriptionDate;


    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @ManyToMany(mappedBy = "members")
    private Set<Task> tasks;
}
