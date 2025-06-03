package com.alertincident.user_service.util;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        String secret = "benjawa22";
        String base64Key = Base64.getEncoder().encodeToString(secret.getBytes());
        System.out.println("Clé Base64: " + base64Key); // YmVuanl3YTIy
    }
}
