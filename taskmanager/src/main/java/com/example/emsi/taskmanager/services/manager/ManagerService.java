package com.example.emsi.taskmanager.services.manager;

import com.example.emsi.taskmanager.entities.manager.Manager;
import com.example.emsi.taskmanager.repository.manager.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager addManager(Manager manager) {
        return managerRepository.save(manager);
    }
}
