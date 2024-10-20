package com.booksotre.DAO.impl;

import java.util.List;

import com.booksotre.DAO.IEmployeeDAO;
import com.booksotre.mapper.impl.EmployeeMapper;
import com.booksotre.model.EmployeeModel;

public class EmployeeDAO extends AbstractDAO<EmployeeModel> implements IEmployeeDAO {
    @Override
    public int checkAccountExistence(String email) {
        String query = "SELECT COUNT(*) FROM Customer WHERE email = ?;";
        return count(query, email);
    }

    @Override
    public void createAccount(EmployeeModel employee) {
        String query =
                """
				INSERT INTO Employee(email, password, employee_name, role, gender, dob, phone, address, salary)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
				""";
        insert(
                query,
                employee.getEmail(),
                employee.getPassword(),
                employee.getEmployeeName(),
                employee.getRole(),
                employee.getGender(),
                employee.getDob(),
                employee.getPhone(),
                employee.getAddress(),
                employee.getSalary());
    }

    @Override
    public EmployeeModel getEmployeeByEmail(String email) {
        String query =
                """
				SELECT employee_id, email, password, name, position, gender, dob, phone, address, create_at
				FROM Employee
				WHERE email = ?;
				""";
        List<EmployeeModel> list = query(query, new EmployeeMapper(), email);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public void updateAccount(EmployeeModel employee) {
        String query =
                """
				UPDATE Employee SET email = ?, name = ?, position = ?, gender = ?, dob = ?, phone = ?, address = ?
				WHERE employee_id = ?;
				""";
        update(
                query,
                employee.getEmail(),
                employee.getEmployeeName(),
                employee.getRole(),
                employee.getGender(),
                employee.getDob(),
                employee.getPhone(),
                employee.getAddress(),
                employee.getEmployeeId());
    }

    @Override
    public void changePassword(String email, String newPassword) {
        String query = """
				UPDATE Employee SET password = ? WHERE email = ?;
				""";
        update(query, newPassword, email);
    }
}
