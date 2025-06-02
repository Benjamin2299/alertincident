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
        if (imageFile != null && !imageFile.isEmpty()) {
            incident.setImage(imageStorageService.saveImage(imageFile));
        }
        incident.setStatus("PENDING");
        return incidentRepository.save(incident);
    }
}
