package com.booksotre.DAO;

import com.booksotre.model.CartItemModel;

import java.util.List;

public interface ICartItemDAO {
    List<CartItemModel> findByCartId(int cartId);

    void saveCartItem(CartItemModel cartItem);
}
