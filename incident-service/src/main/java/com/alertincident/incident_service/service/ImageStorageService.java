package com.alertincident.incident_service.service;

import com.alertincident.incident_service.model.Image;
import com.alertincident.incident_service.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final ImageRepository imageRepository;

    private static final String UPLOAD_DIR = "uploads/"; // Répertoire de stockage

    @Autowired
    public ImageStorageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // Enregistre une image sur le disque et en base
    public Image saveImage(MultipartFile file) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Générer un nom unique pour éviter les conflits
            String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.\\-_]", "_");
            String filePath = UPLOAD_DIR + uniqueName;

            // Sauvegarder physiquement le fichier
            File destFile = new File(filePath);
            file.transferTo(destFile);

            // Sauvegarder les métadonnées en base
            Image image = new Image();
            image.setFilePath(filePath);
            image.setUploadDate(LocalDateTime.now());

            return imageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image : " + e.getMessage());
        }
    }
}
