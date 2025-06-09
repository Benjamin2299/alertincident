package com.alertincident.incident_service.repository;

// Repository pour l'entité Incident avec méthode personnalisée de recherche par statut

import com.alertincident.incident_service.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByStatus(String status);
}
