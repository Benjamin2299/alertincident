package com.alertincident.user_service.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Stocké haché

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // Ex: "ROLE_USER", "ROLE_ADMIN"

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private NotificationPreferences preferences;
}
