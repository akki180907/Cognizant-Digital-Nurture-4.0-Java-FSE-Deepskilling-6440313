package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "akshaya";
        int attempts = 3;

        logger.info("User '{}' has logged in.", username);
        logger.warn("User '{}' has attempted to log in {} times unsuccessfully.", username, attempts);
        logger.error("Login attempt failed for user '{}'", username);
    }
}
