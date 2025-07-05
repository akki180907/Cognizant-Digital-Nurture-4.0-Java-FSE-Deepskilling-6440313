package com.example.employeemanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String[] beans = context.getBeanDefinitionNames();
        for (String bean : beans) {
            if (bean.contains("employeeRepository") || bean.contains("departmentRepository")) {
                System.out.println("✅ Loaded: " + bean);
            }
        }
    }
}
