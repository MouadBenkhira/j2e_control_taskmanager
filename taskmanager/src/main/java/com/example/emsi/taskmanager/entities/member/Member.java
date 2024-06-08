package com.example.emsi.taskmanager.entities.member;

import com.example.emsi.taskmanager.entities.task.Task;
import com.example.emsi.taskmanager.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Member extends User {
    @ManyToMany
    private Set<Task> tasks;
}
