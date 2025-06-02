package com.alertincident.user_service.service;

import com.alertincident.user_service.dto.UserRegistrationDTO;
import com.alertincident.user_service.dto.UserResponseDTO;
import com.alertincident.user_service.model.User;
import com.alertincident.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // === Méthode existante ===
    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(user);
        return convertToDTO(user);
    }

    // === NOUVELLES MÉTHODES AJOUTÉES ===

    // 1. Récupère un utilisateur par son ID (GET /api/users/{id})
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDTO(user);
    }

    // 2. Récupère tous les utilisateurs (GET /api/users)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 3. Met à jour un utilisateur (PUT /api/users/{id})
    public UserResponseDTO updateUser(Long id, UserRegistrationDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Mise à jour des champs
        user.setEmail(dto.getEmail());
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        
        // Mise à jour sécurisée du mot de passe
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    // 4. Supprime un utilisateur (DELETE /api/users/{id})
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // === Méthode helper interne ===
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setRoles(user.getRoles());
        return dto;
    }
}