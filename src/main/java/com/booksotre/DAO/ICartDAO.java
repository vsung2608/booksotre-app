package com.booksotre.DAO;

import com.booksotre.model.CartModel;

public interface ICartDAO{

    CartModel findCartByCustomerId(int customerId);
}
