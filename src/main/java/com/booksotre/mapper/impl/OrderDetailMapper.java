package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.OrderDetailModel;

public class OrderDetailMapper implements IRowMapper<OrderDetailModel> {
    @Override
    public OrderDetailModel mapper(ResultSet rs) {
        OrderDetailModel orderDetail;
        try {
            orderDetail = OrderDetailModel.builder()
                    .orderDetailId(rs.getInt("order_detail_id"))
                    .orderId(rs.getInt("order_id"))
                    .quantity(rs.getInt("quantity"))
                    .bookId(rs.getInt("book_id"))
                    .price(rs.getDouble("price"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetail;
    }
}
