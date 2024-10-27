package com.booksotre.service.impl;

import com.booksotre.DAO.IOrderDAO;
import com.booksotre.DAO.impl.OrderDAO;
import com.booksotre.model.OrdersModel;
import com.booksotre.service.IOrderService;

import java.util.LinkedHashMap;
import java.util.List;

public class OrderService implements IOrderService {
    private final IOrderDAO orderDAO = new OrderDAO();

    @Override
    public List<OrdersModel> findAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    public List<OrdersModel> findAllOrdersByStatus(String status) {
        return orderDAO.findByStatus(status);
    }

    @Override
    public List<OrdersModel> findAllByDate(String from, String to) {
        return orderDAO.findByDate(from, to);
    }

    @Override
    public LinkedHashMap<String, Integer> countByDate(String from, String to, String status) {
        return orderDAO.countByDateAndStatus(from, to, status);
    }

    @Override
    public double getTotalIncome() {
        return orderDAO.getTotalIncome();
    }

    @Override
    public int countByStatus(String status) {
        return orderDAO.countByStatus(status);
    }
}
