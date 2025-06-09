package com.alertincident.incident_service.controller;

import com.alertincident.incident_service.model.DeviceLocation;
import com.alertincident.incident_service.model.Incident;
import com.alertincident.incident_service.repository.IncidentRepository;
import com.alertincident.incident_service.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;
    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentController(IncidentService incidentService,
                              IncidentRepository incidentRepository) {
        this.incidentService = incidentService;
        this.incidentRepository = incidentRepository;
    }

    // Création d’un incident
    @PostMapping
public ResponseEntity<Incident> createIncident(
        @RequestBody Incident incident,
        @RequestHeader("X-Device-Latitude") Double latitude,
        @RequestHeader("X-Device-Longitude") Double longitude) {

    DeviceLocation location = new DeviceLocation(latitude, longitude);
    incident.setDeviceLocation(location);

    // Appel de la logique métier
    Incident savedIncident = incidentService.reportIncident(incident, null); // si pas d’image

    return ResponseEntity.ok(savedIncident);
}


    // Tous les incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Incidents par statut
    @GetMapping("/status/{status}")
    public List<Incident> getIncidentsByStatus(@PathVariable String status) {
        return incidentRepository.findByStatus(status.toUpperCase());
    }
}
