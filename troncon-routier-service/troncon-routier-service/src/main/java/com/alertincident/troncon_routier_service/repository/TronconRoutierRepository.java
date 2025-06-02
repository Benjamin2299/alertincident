package com.alertincident.troncon_routier_service.repository;


import com.alertincident.troncon_routier_service.model.TronconRoutier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TronconRoutierRepository extends JpaRepository<TronconRoutier, Long> {
}

