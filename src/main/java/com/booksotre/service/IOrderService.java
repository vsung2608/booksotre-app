package com.booksotre.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.model.OrdersModel;

public interface IOrderService {

    List<OrdersModel> findAllOrders();

    int saveOrder(OrdersModel order);

    List<OrdersModel> findAllOrdersByStatus(String status);

    List<OrdersModel> findAllByDate(String from, String to);

    LinkedHashMap<String, Integer> countByDate(String from, String to, String status);

    double getTotalIncome();

    int countByStatus(String status, int customerId);

    void confirmOrder(int orderId);

    void cancelOrder(int orderId);
}
