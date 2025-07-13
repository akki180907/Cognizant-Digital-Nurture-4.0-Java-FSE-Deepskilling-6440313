package com.example.resthands.dao;

import com.example.resthands.model.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {

    private static List<Department> DEPARTMENT_LIST;

    public DepartmentDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        DEPARTMENT_LIST = (List<Department>) context.getBean("departmentList");
        System.out.println("🔹 DepartmentDao loaded departments from XML.");
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}
