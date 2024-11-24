package com.booksotre.mapper.impl;

import com.booksotre.DAO.impl.CartItemDAO;
import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.CartItemModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements IRowMapper<CartItemModel> {
    @Override
    public CartItemModel mapper(ResultSet rs) {
        CartItemModel cartItem = null;
        try{
            cartItem = CartItemModel.builder()
                    .cartId(rs.getInt("cart_id"))
                    .cartItemId(rs.getInt("cart_item_id"))
                    .bookId(rs.getInt("book_id"))
                    .price(rs.getBigDecimal("price"))
                    .quantity(rs.getInt("quantity"))
                    .build();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartItem;
    }
}
