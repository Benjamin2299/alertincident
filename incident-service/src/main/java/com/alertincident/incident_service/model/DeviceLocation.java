package com.alertincident.incident_service.model;

/**
 * Représente une localisation géographique par latitude et longitude.
 * Utilisée pour associer les coordonnées de l'utilisateur à un incident.
 */
public class DeviceLocation {

    private Double latitude;
    private Double longitude;

    public DeviceLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
