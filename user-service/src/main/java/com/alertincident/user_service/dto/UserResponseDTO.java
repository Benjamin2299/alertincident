package com.alertincident.user_service.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private Set<String> roles;
}
