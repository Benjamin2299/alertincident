package com.alertincident.incident_service.controller;

import com.alertincident.incident_service.model.DeviceLocation;
import com.alertincident.incident_service.model.Incident;
import com.alertincident.incident_service.repository.IncidentRepository;
import com.alertincident.incident_service.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IncidentService incidentService;
    private final IncidentRepository incidentRepository; // Ajout du repository manquant

    @Autowired
    public IncidentController(IncidentService incidentService, 
                            IncidentRepository incidentRepository) {
        this.incidentService = incidentService;
        this.incidentRepository = incidentRepository;
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(
        @RequestBody Incident incident,
        @RequestHeader("X-Device-Latitude") Double latitude,
        @RequestHeader("X-Device-Longitude") Double longitude) {
    
        // 1. Récupérer la position
        DeviceLocation location = new DeviceLocation(latitude, longitude);
        incident.setDeviceLocation(location);
    
        // 2. Mapper vers les champs persistants
        incident.setLatitude(location.getLatitude());
        incident.setLongitude(location.getLongitude());
    
        // 3. Sauvegarder
        Incident savedIncident = incidentRepository.save(incident);
    
        return ResponseEntity.ok(savedIncident);
    }
}