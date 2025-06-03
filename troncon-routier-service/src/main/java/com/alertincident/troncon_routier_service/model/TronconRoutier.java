package com.alertincident.troncon_routier_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TronconRoutier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTroncon;
    private Double latitude;
    private Double longitude;
    private String typeRoute;
    private Double longueur;
    private LocalDate dateDerniereMiseAJour;
}

