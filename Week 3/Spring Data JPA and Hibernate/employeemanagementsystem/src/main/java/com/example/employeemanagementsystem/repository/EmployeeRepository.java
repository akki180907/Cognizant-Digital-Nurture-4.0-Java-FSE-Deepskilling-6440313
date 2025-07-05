package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);

    List<Employee> findByDepartment(Department department);

    List<Employee> findAllByOrderByNameAsc();


    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.department.id = :deptId")
    List<Employee> findByNameAndDepartment(@Param("name") String name, @Param("deptId") Long deptId);


    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:domain%")
    List<Employee> findByEmailDomain(@Param("domain") String domain);

    @Query("SELECT e.name AS name, e.email AS email, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeDTO> findAllEmployeeSummaries();
}
