package com.booksotre.service;

import com.booksotre.model.EmployeeModel;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeModel> getAllEmployees();

    boolean checkAccountExist(String email);

    void createAccount(EmployeeModel employee);

    boolean passwordValid(String email, String password);

    EmployeeModel getEmployeeByEmail(String email);

    void updateEmployee(EmployeeModel employee);

    void changePassword(String email, String newPassword);

    void updateProfile(EmployeeModel employee);

    void deleteEmployee(int id);

    List<EmployeeModel> findEmployee(String name);
}
