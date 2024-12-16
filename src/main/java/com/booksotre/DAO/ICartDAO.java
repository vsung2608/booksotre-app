package com.booksotre.DAO;

import com.booksotre.model.CartModel;

public interface ICartDAO{

    CartModel findCartByCustomerId(int customerId);

    void resetCart(int id);

    void updateCart(int quantity, double price, int id);
}
