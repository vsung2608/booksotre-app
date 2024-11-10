package com.booksotre.mapper.impl;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.CartModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements IRowMapper<CartModel> {
    @Override
    public CartModel mapper(ResultSet rs) {
        CartModel cart = null;
        try{
            cart = CartModel.builder()
                    .cartId(rs.getInt("cart_id"))
                    .customerId(rs.getInt("customer_id"))
                    .totalAmount(rs.getInt("total_amout"))
                    .totalPrice(rs.getDouble("total_price"))
                    .build();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cart;
    }
}
