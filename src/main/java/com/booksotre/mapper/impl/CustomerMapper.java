package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.CustomerModel;

public class CustomerMapper implements IRowMapper<CustomerModel> {
    @Override
    public CustomerModel mapper(ResultSet rs) {
        CustomerModel customerModel;
        try {
            customerModel = CustomerModel.builder()
                    .customerId(rs.getInt("customer_id"))
                    .customerName(rs.getString("customer_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .address(rs.getString("address"))
                    .password(rs.getString("password"))
                    .gender(rs.getString("gender"))
                    .dob(rs.getString("dob"))
                    .createAt(rs.getTimestamp("create_at"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerModel;
    }
}
