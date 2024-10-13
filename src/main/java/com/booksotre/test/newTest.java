package com.booksotre.test;

import com.booksotre.DAO.IEmployeeDAO;
import com.booksotre.DAO.impl.EmployeeDAO;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.EmployeeService;


public class newTest {
    public static void main(String[] args) {
        String passEn = "$2a$10$uQv4WO6Im2YvWZ8z3VfBp.JAKTtqcK83w4qzsSLTMtFhPI9JDR2Wi";
        IEmployeeService employeeService = new EmployeeService();
        IEmployeeDAO employeeDAO = new EmployeeDAO();
        System.out.println(employeeService.passwordValid("vsung2608@gmail.com", "vsung2608"));
    }
}
