package com.alertincident.user_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.alertincident.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    // Méthode standard fournie par Spring Data JPA
    Optional<User> findByEmail(String email);

    // Méthode personnalisée (requête JPQL)
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> findByEmailAndPassword(
        @Param("email") String email, 
        @Param("password") String password
    );
}
