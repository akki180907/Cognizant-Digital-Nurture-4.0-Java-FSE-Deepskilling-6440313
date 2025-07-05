package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            throw new IllegalArgumentException("Department ID must be provided.");
        }

        Long deptId = employee.getDepartment().getId();
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department with ID " + deptId + " not found"));

        employee.setDepartment(dept);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getEmployeesByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public List<Employee> getEmployeesByDepartment(Long deptId) {
        Department dept = departmentRepository.findById(deptId).orElse(null);
        return employeeRepository.findByDepartment(dept);
    }

    public List<Employee> getAllEmployeesSortedByName() {
        return employeeRepository.findAllByOrderByNameAsc();
    }

    public Page<Employee> getEmployeesPagedAndSorted(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> getByNameAndDepartment(String name, Long deptId) {
        return employeeRepository.findByNameAndDepartment(name, deptId);
    }

    public List<Employee> getByEmailDomain(String domain) {
        return employeeRepository.findByEmailDomain(domain);
    }

    public List<EmployeeDTO> getEmployeeSummaries() {
        return employeeRepository.findAllEmployeeSummaries();
    }
}
