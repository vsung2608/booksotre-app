package com.booksotre.service;

import com.booksotre.model.OrdersModel;

import java.util.LinkedHashMap;
import java.util.List;

public interface IOrderService {

    List<OrdersModel> findAllOrders();

    List<OrdersModel> findAllOrdersByStatus(String status);

    List<OrdersModel> findAllByDate(String from, String to);

    LinkedHashMap<String, Integer> countByDate(String from, String to, String status);

    double getTotalIncome();

    int countByStatus(String status);
}
