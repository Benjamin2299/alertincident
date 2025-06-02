package com.alertincident.user_service.service;

import com.alertincident.user_service.model.NotificationPreferences;
import com.alertincident.user_service.repository.NotificationPreferencesRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationPreferencesService {
    private final NotificationPreferencesRepository preferencesRepository;

    public NotificationPreferencesService(NotificationPreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    public NotificationPreferences updatePreferences(Long userId, NotificationPreferences newPrefs) {
        NotificationPreferences prefs = preferencesRepository.findByUser_Id(userId) // Modifié ici
                                  .orElseThrow(() -> new RuntimeException("Preferences not found"));
        prefs.setEmailEnabled(newPrefs.isEmailEnabled());
        prefs.setSmsEnabled(newPrefs.isSmsEnabled());
        prefs.setPushEnabled(newPrefs.isPushEnabled());
        return preferencesRepository.save(prefs);
    }
}