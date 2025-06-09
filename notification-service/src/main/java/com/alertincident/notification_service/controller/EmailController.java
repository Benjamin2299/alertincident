package com.alertincident.notification_service.controller;

import com.alertincident.notification_service.model.EmailDetails;
import com.alertincident.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    /**
     * Endpoint pour envoyer un email simple avec un corps JSON.
     * @param emailDetails Contient "to", "subject" et "body"
     * @return Message de confirmation
     */
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDetails emailDetails) {
        emailService.sendEmail(
            emailDetails.getTo(),
            emailDetails.getSubject(),
            emailDetails.getBody()
        );
        return "Email envoyé avec succès à " + emailDetails.getTo();
    }
}
