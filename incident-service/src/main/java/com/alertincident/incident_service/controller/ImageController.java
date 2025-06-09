package com.alertincident.incident_service.controller;

import com.alertincident.incident_service.model.Image;
import com.alertincident.incident_service.repository.ImageRepository;
import com.alertincident.incident_service.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageStorageService imageStorageService;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageStorageService imageStorageService, ImageRepository imageRepository) {
        this.imageStorageService = imageStorageService;
        this.imageRepository = imageRepository;
    }

    // Téléversement
    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(imageStorageService.saveImage(file));
    }

    // Consultation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
