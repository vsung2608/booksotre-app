package com.booksotre.mapper.impl;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.EmployeeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements IRowMapper<EmployeeModel> {
    @Override
    public EmployeeModel mapper(ResultSet rs) {
        EmployeeModel employeeModel;
        try {
            employeeModel = EmployeeModel.builder()
                    .employeeId(rs.getInt("employee_id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .position(rs.getString("position"))
                    .address(rs.getString("address"))
                    .password(rs.getString("password"))
                    .gender(rs.getString("gender"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeModel;
    }
}
