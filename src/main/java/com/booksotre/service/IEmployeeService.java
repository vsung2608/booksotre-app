package com.booksotre.service;

import com.booksotre.model.EmployeeModel;

public interface IEmployeeService {
    boolean checkAccountExist(String email);

    void createAccount(EmployeeModel employee);

    boolean passwordValid(String email, String password);

    EmployeeModel getEmployeeByEmail(String email);

    void updateEmployee(EmployeeModel employee);

    void changePassword(String email, String newPassword);
}
