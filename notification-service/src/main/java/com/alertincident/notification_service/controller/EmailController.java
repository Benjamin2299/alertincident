package com.alertincident.notification_service.controller;


import com.alertincident.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email envoyé avec succès à " + to;
    }
}

