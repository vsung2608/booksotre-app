package com.booksotre.DAO.impl;

import com.booksotre.DAO.ICartItemDAO;
import com.booksotre.mapper.impl.CartItemMapper;
import com.booksotre.model.CartItemModel;

import java.util.List;

public class CartItemDAO extends AbstractDAO<CartItemDAO> implements ICartItemDAO {

    @Override
    public List<CartItemModel> findByCartId(int cartId) {
        String query = """
                    SELECT cart_item_id, cart_id, book_id, quantity, price
                    FROM Cart_item
                    WHERE cart_id = ?;
                """;
        return query(query, new CartItemMapper(), cartId);
    }
}
