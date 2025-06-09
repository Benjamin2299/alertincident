package com.alertincident.incident_service.service;

import com.alertincident.incident_service.model.Incident;
import com.alertincident.incident_service.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository,
                           ImageStorageService imageStorageService) {
        this.incidentRepository = incidentRepository;
        this.imageStorageService = imageStorageService;
    }

    public Incident reportIncident(Incident incident, MultipartFile imageFile) {

        // Utiliser la localisation de l'utilisateur si latitude ou longitude est manquante
        if (incident.getDeviceLocation() != null) {
            if (incident.getLatitude() == null || incident.getLongitude() == null) {
                incident.updateFromDeviceLocation();
            }
        }

        // Enregistrer l’image si fournie
        if (imageFile != null && !imageFile.isEmpty()) {
            incident.setImage(imageStorageService.saveImage(imageFile));
        }

        // Statut initial par défaut
        incident.setStatus("en attente");

        return incidentRepository.save(incident);
    }
}
