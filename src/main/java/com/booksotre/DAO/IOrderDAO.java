package com.booksotre.DAO;

import com.booksotre.model.OrdersModel;

import java.util.LinkedHashMap;
import java.util.List;

public interface IOrderDAO {
    List<OrdersModel> findAll();

    List<OrdersModel> findByStatus(String status);

    List<OrdersModel> findByDate(String from, String to);

    LinkedHashMap<String, Integer> countByDateAndStatus(String from, String to, String status);

    int countByStatus(String status);

    Double getTotalIncome();

}
