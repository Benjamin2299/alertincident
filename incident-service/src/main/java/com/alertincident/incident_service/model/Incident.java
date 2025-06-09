package com.alertincident.incident_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    private String description;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Transient
    private DeviceLocation deviceLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public Incident() {
        this.dateTime = LocalDateTime.now();
        this.status = "en attente"; // par défaut
    }

    public Incident(String type, String description, Double latitude, Double longitude) {
        this();
        this.type = type;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deviceLocation = new DeviceLocation(latitude, longitude);
    }

    // Getters & setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateTime() { return dateTime; }

    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public DeviceLocation getDeviceLocation() { return deviceLocation; }

    public void setDeviceLocation(DeviceLocation deviceLocation) {
        this.deviceLocation = deviceLocation;
        if (deviceLocation != null) {
            this.latitude = deviceLocation.getLatitude();
            this.longitude = deviceLocation.getLongitude();
        }
    }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    // Met à jour les coordonnées à partir de deviceLocation
    public void updateFromDeviceLocation() {
        if (this.deviceLocation != null) {
            this.latitude = this.deviceLocation.getLatitude();
            this.longitude = this.deviceLocation.getLongitude();
        }
    }
}
