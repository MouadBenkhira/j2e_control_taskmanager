package com.example.emsi.taskmanager.repository.member;

import com.example.emsi.taskmanager.entities.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Manager, Long> {

}
