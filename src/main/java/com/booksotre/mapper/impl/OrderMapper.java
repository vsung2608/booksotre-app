package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.OrdersModel;

public class OrderMapper implements IRowMapper<OrdersModel> {
    @Override
    public OrdersModel mapper(ResultSet rs) {
        OrdersModel order;
        try {
            order = OrdersModel.builder()
                    .orderId(rs.getInt("order_id"))
                    .customerId(rs.getInt("customer_id"))
                    .employeeId(rs.getInt("employee_id"))
                    .totalAmount(rs.getInt("total_amout"))
                    .totalPrice(rs.getDouble("total_price"))
                    .orderStatus(rs.getString("order_status"))
                    .createAt(rs.getTimestamp("create_at"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
