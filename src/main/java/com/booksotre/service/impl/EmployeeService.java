package com.booksotre.service.impl;

import com.booksotre.DAO.IEmployeeDAO;
import com.booksotre.DAO.impl.EmployeeDAO;
import com.booksotre.model.EmployeeModel;
import com.booksotre.service.IEmployeeService;
import com.booksotre.utils.PasswordUtil;


public class EmployeeService implements IEmployeeService {

    private final IEmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean checkAccountExist(String email){
        return employeeDAO.checkAccountExistence(email) != 0;
    }

    @Override
    public void createAccount(EmployeeModel employee) {
        employee.setPassword(PasswordUtil.hashPassword(employee.getPassword()));
        employeeDAO.createAccount(employee);
    }

    @Override
    public boolean passwordValid(String email, String password) {
        EmployeeModel employee = employeeDAO.getEmployeeByEmail(email);
        return PasswordUtil.checkPassword(password, employee.getPassword());
    }
}
