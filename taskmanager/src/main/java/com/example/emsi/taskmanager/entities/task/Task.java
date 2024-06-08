package com.example.emsi.taskmanager.entities.task;

import com.example.emsi.taskmanager.entities.member.Member;
import com.example.emsi.taskmanager.entities.notification.Notification;
import com.example.emsi.taskmanager.entities.project.Project;
import com.example.emsi.taskmanager.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int priority;

    @OneToMany(mappedBy = "task")
    private List<Notification> notifications;

    private LocalDate startDate;

    @ManyToMany
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members;

    private LocalDate endDate;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
