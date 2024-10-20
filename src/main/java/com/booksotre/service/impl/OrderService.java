package com.booksotre.service.impl;

import com.booksotre.DAO.IOrderDAO;
import com.booksotre.DAO.impl.OrderDAO;
import com.booksotre.model.OrdersModel;
import com.booksotre.service.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
    private final IOrderDAO orderDAO = new OrderDAO();

    @Override
    public List<OrdersModel> findAllOrders() {
        return orderDAO.findAll();
    }
}
