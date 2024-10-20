package com.booksotre.service;

import com.booksotre.model.OrdersModel;

import java.util.List;

public interface IOrderService {

    List<OrdersModel> findAllOrders();
}
