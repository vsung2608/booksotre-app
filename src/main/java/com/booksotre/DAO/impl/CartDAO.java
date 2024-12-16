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

    @Override
    public void resetCart(int id) {
        String query = """
                UPDATE Cart SET total_amout = 0, total_price = 0 WHERE cart_id = ?
                """;
        update(query, id);
    }

    @Override
    public void updateCart(int quantity, double price, int id) {
        String query = "UPDATE Cart SET total_amout = total_amout + ?, total_price = total_price + ? WHERE cart_id = ?";
        update(query, quantity, price, id);
    }
}
