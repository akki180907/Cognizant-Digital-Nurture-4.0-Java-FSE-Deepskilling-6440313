package com.example.resthand.service;

import com.example.resthand.dao.EmployeeDao;
import com.example.resthand.exception.EmployeeNotFoundException;
import com.example.resthand.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        dao.updateEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        dao.deleteEmployee(id);
    }

    public void addEmployee(Employee employee) {
        dao.addEmployee(employee);
    }
}
