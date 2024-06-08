package com.example.emsi.taskmanager.repository.notification;

import com.example.emsi.taskmanager.entities.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
