package com.booksotre.DAO.impl;

import com.booksotre.DAO.ICartDAO;
import com.booksotre.mapper.impl.CartMapper;
import com.booksotre.model.CartModel;

import java.util.List;

public class CartDAO extends AbstractDAO<CartModel> implements ICartDAO {
    @Override
    public CartModel findCartByCustomerId(int customerId) {
        String query = """
                SELECT cart_id, customer_id, total_amout, total_price
                FROM Cart
                WHERE customer_id = ?
                """;
        List<CartModel> list = query(query, new CartMapper(), customerId);
        return list.isEmpty() ? null : (CartModel) list.getFirst();
    }
}
