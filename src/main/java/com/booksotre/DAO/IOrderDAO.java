package com.booksotre.DAO;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.model.OrdersModel;

public interface IOrderDAO {
    List<OrdersModel> findAll();

    int saveOrder(OrdersModel order);

    List<OrdersModel> findByStatus(String status);

    List<OrdersModel> findByDate(String from, String to);

    LinkedHashMap<String, Integer> countByDateAndStatus(String from, String to, String status);

    int countByStatus(String status, int customerId);

    Double getTotalIncome();

    void setStatusOrder(int id, String status);
}
