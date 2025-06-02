package com.alertincident.incident_service.service;

import com.alertincident.incident_service.model.Image;
import com.alertincident.incident_service.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImageStorageService {

    private final ImageRepository imageRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    public ImageStorageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile file) {
        try {
            // Créer le dossier uploads s’il n’existe pas
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Définir le chemin du fichier
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            File destFile = new File(filePath);

            // Sauvegarder le fichier sur le disque
            file.transferTo(destFile);

            // Enregistrer l’image en base de données
            Image image = new Image();
            image.setFilePath(filePath); // ou "/uploads/" + file.getOriginalFilename()
            image.setUploadDate(LocalDateTime.now());

            return imageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image : " + e.getMessage());
        }
    }
}
