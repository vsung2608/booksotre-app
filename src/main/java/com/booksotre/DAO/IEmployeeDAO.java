package com.booksotre.DAO;


import com.booksotre.model.EmployeeModel;

public interface IEmployeeDAO {
    int checkAccountExistence(String email);
    void createAccount(EmployeeModel employee);
    EmployeeModel getEmployeeByEmail(String email);
}
