package com.example.resthand.dao;

import com.example.resthand.exception.EmployeeNotFoundException;
import com.example.resthand.model.Department;
import com.example.resthand.model.Employee;
import com.example.resthand.model.Skill;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class EmployeeDao {

    private static List<Employee> employees = new ArrayList<>();

    static {
        // Sample employee for testing
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Akshaya");
        e.setSalary(60000.0);
        e.setPermanent(true);

        try {
            e.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Department d = new Department();
        d.setId(101L);
        d.setName("Engineering");
        e.setDepartment(d);

        Skill s1 = new Skill();
        s1.setId(1L);
        s1.setName("Java");

        Skill s2 = new Skill();
        s2.setId(2L);
        s2.setName("Spring Boot");

        e.setSkills(Arrays.asList(s1, s2));

        employees.add(e);
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        boolean removed = employees.removeIf(emp -> emp.getId().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())) {
                employees.set(i, employee);
                found = true;
                break;
            }
        }
        if (!found) throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}
