package com.example.resthands.service;

import com.example.resthands.dao.EmployeeDao;
import com.example.resthands.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional  
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();  
    }
}
