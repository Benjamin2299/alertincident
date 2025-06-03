package com.alertincident.incident_service.model;

public class DeviceLocation {
    private Double latitude;
    private Double longitude;

    // Constructeur
    public DeviceLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    // Optionnel : Ajoutez des setters si nécessaire
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
