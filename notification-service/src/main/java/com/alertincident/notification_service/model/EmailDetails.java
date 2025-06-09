package com.alertincident.notification_service.model;


import lombok.Data;

@Data
public class EmailDetails {
    private String to;
    private String subject;
    private String body;
}

