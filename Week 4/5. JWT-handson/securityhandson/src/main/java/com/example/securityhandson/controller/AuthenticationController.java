package com.example.securityhandson.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "ThisIsASecretKeyForJwtDontShare!".getBytes(StandardCharsets.UTF_8)
    );

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Authentication request received.");
        String username = getUser(authHeader); // ✅ calling your new method
        LOGGER.debug("Extracted Username: {}", username);

        String token = generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    // ✅ Step-by-step decoding of Basic Auth Header
    private String getUser(String authHeader) {
        LOGGER.debug("Raw Authorization Header: {}", authHeader);

        // Step 1: Get Base64 part after "Basic "
        String base64Credentials = authHeader.substring("Basic ".length());

        // Step 2: Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);

        // Step 3: Convert bytes to string
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8); // e.g. "user:pwd"
        LOGGER.debug("Decoded Credentials: {}", decodedString);

        // Step 4: Extract username before the colon
        String username = decodedString.split(":")[0];
        LOGGER.debug("Extracted Username from credentials: {}", username);

        return username;
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
