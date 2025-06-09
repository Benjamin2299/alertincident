package com.alertincident.incident_service.repository;

// Repository pour gérer les opérations CRUD sur les entités Image

import com.alertincident.incident_service.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}