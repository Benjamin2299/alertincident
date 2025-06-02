package com.alertincident.user_service.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String nom;
    private String prenom;
}