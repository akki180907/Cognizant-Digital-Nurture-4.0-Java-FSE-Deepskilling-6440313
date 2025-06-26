package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleAppenderExample {
    private static final Logger logger = LoggerFactory.getLogger(MultipleAppenderExample.class);

    public static void main(String[] args) {
        logger.debug("Debug level log - useful for developers.");
        logger.info("Info level log - general information.");
        logger.warn("Warning level log - something unexpected happened.");
        logger.error("Error level log - an error occurred.");
    }
}
