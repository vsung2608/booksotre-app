package com.booksotre.service;

import com.booksotre.model.CartItemModel;
import com.booksotre.model.CartModel;

import java.util.List;

public interface ICartService {

    CartModel findByCustomerId(int customerId);

    List<CartItemModel> findByOrderId(int orderId);
}
