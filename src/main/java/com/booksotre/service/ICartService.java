package com.booksotre.service;

import com.booksotre.model.CartItemModel;
import com.booksotre.model.CartModel;

import java.util.List;

public interface ICartService {

    CartModel findByCustomerId(int customerId);

    List<CartItemModel> findByCartId(int cartId);

    void addCartItem(CartItemModel cartItemModel);

    void deleteCartItem(int cartId);

    void resetCart(int id);

    void updateCart(int quantity, double price, int id);
}
