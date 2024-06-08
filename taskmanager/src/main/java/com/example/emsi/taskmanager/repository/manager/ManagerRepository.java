package com.example.emsi.taskmanager.repository.manager;

import com.example.emsi.taskmanager.entities.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
