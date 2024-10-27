package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.EmployeeModel;

public class EmployeeMapper implements IRowMapper<EmployeeModel> {
    @Override
    public EmployeeModel mapper(ResultSet rs) {
        EmployeeModel employeeModel;
        try {
            employeeModel = EmployeeModel.builder()
                    .employeeId(rs.getInt("employee_id"))
                    .employeeName(rs.getString("employee_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .role(rs.getString("role"))
                    .address(rs.getString("address"))
                    .password(rs.getString("password"))
                    .gender(rs.getString("gender"))
                    .dob(rs.getString("create_at"))
                    .salary(rs.getDouble("salary"))
                    .dob(String.valueOf(rs.getDate("dob")))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeModel;
    }
}
