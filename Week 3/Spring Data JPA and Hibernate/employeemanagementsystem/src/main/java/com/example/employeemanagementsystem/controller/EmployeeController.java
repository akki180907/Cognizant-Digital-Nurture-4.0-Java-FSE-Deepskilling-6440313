package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id).orElse(null);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/email/{email}")
    public List<Employee> getByEmail(@PathVariable String email) {
        return employeeService.getEmployeesByEmail(email);
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getByDepartment(@PathVariable Long deptId) {
        return employeeService.getEmployeesByDepartment(deptId);
    }

    @GetMapping("/sorted/name")
    public List<Employee> getSortedByName() {
        return employeeService.getAllEmployeesSortedByName();
    }

    @GetMapping("/paged")
    public Page<Employee> getPagedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return employeeService.getEmployeesPagedAndSorted(page, size, sortBy);
    }

    @GetMapping("/filter")
    public List<Employee> filterByNameAndDepartment(
            @RequestParam String name,
            @RequestParam Long deptId) {
        return employeeService.getByNameAndDepartment(name, deptId);
    }

    @GetMapping("/email/filter")
    public List<Employee> filterByEmailDomain(@RequestParam String domain) {
        return employeeService.getByEmailDomain(domain);
    }

    
    @GetMapping("/summary")
    public List<EmployeeDTO> getEmployeeSummary() {
        return employeeService.getEmployeeSummaries();
    }
} 
