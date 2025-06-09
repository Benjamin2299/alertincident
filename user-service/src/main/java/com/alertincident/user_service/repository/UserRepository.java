package com.alertincident.user_service.repository;

import com.alertincident.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Vérifie si un utilisateur existe avec un email donné
    boolean existsByEmail(String email);

    // Trouve un utilisateur par email
    Optional<User> findByEmail(String email);

    // Authentification simple : trouver un utilisateur par email + mot de passe (non sécurisé, pas recommandé)
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> findByEmailAndPassword(
        @Param("email") String email,
        @Param("password") String password
    );
}
