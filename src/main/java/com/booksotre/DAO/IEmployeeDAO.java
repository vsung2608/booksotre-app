package com.booksotre.DAO;

import com.booksotre.model.EmployeeModel;

import java.util.List;

public interface IEmployeeDAO {
    List<EmployeeModel> getAllEmployee();

    int checkAccountExistence(String email);

    void createAccount(EmployeeModel employee);

    EmployeeModel getEmployeeByEmail(String email);

    void updateAccount(EmployeeModel employee);

    void changePassword(String email, String newPassword);

    void updateMyInfo(EmployeeModel employee);

    void deleteEmployee(int id);

    List<EmployeeModel> findEmployeeByName(String name);
}
