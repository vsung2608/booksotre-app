package com.booksotre.service.impl;

import com.booksotre.DAO.IEmployeeDAO;
import com.booksotre.DAO.impl.EmployeeDAO;
import com.booksotre.model.EmployeeModel;
import com.booksotre.service.IEmployeeService;
import com.booksotre.utils.PasswordUtil;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    private final IEmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public List<EmployeeModel> getAllEmployees() {
        return employeeDAO.getAllEmployee();
    }

    public boolean checkAccountExist(String email) {
        System.out.println(employeeDAO.checkAccountExistence(email));
        return employeeDAO.checkAccountExistence(email) == 0;
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

    @Override
    public EmployeeModel getEmployeeByEmail(String email) {
        return employeeDAO.getEmployeeByEmail(email);
    }

    @Override
    public void updateEmployee(EmployeeModel employee) {
        employeeDAO.updateAccount(employee);
    }

    @Override
    public void changePassword(String email, String newPassword) {
        String hashPass = PasswordUtil.hashPassword(newPassword);
        employeeDAO.changePassword(email, hashPass);
    }

    @Override
    public void updateProfile(EmployeeModel employee) {
        employeeDAO.updateMyInfo(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public List<EmployeeModel> findEmployee(String name) {
        return employeeDAO.findEmployeeByName(name);
    }
}
