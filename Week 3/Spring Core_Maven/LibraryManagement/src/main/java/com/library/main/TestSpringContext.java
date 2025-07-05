package com.library.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringContext {
    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext();  // basic Spring context creation
            System.out.println("Spring context loaded successfully!");
        } catch (Exception e) {
            System.err.println("Failed to load Spring context: " + e.getMessage());
        }
    }
}
