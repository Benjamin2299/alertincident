package com.alertincident.user_service.repository;

import com.alertincident.user_service.model.NotificationPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NotificationPreferencesRepository extends JpaRepository<NotificationPreferences, Long> {
    Optional<NotificationPreferences> findByUser_Id(Long userId); // Syntaxe JPA correcte
}