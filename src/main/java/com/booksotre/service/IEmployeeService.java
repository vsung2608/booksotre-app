package com.booksotre.service;

import com.booksotre.model.EmployeeModel;

public interface IEmployeeService {
    boolean checkAccountExist(String email);
    void createAccount(EmployeeModel employee);
    boolean passwordValid(String email, String password);
}
