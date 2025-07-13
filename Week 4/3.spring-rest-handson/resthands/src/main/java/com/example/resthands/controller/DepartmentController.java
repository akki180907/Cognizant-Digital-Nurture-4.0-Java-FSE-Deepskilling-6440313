package com.example.resthands.controller;

import com.example.resthands.model.Department;
import com.example.resthands.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        System.out.println("✅ /departments REST API called.");
        return departmentService.getAllDepartments();
    }
}
