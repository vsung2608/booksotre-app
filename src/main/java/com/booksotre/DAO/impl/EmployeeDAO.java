package com.booksotre.DAO.impl;

import com.booksotre.DAO.IEmployeeDAO;
import com.booksotre.mapper.impl.EmployeeMapper;
import com.booksotre.model.EmployeeModel;

import java.util.List;

public class EmployeeDAO extends AbstractDAO<EmployeeModel> implements IEmployeeDAO {
    @Override
    public int checkAccountExistence(String email) {
        String query = "SELECT COUNT(*) FROM Employees WHERE email = ?;";
        return count(query, email);
    }

    @Override
    public void createAccount(EmployeeModel employee) {
        String query = """
                INSERT INTO Employees(email, password, name, position, gender, dob, phone, address, create_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        insert(query, employee.getEmail(), employee.getPassword(), employee.getName(), employee.getPosition(),
                employee.getGender(), employee.getDob(), employee.getPhone(), employee.getAddress(), employee.getCreate_at());
    }

    @Override
    public EmployeeModel getEmployeeByEmail(String email) {
        String query = """
                 SELECT employee_id, email, password, name, position, gender, dob, phone, address, create_at
                 FROM Employees
                 WHERE email = ?;
                 """;
        List<EmployeeModel> list = query(query, new EmployeeMapper(), email);
        return list.isEmpty() ? null : list.getFirst();
    }
}
