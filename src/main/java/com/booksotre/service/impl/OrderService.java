package com.booksotre.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.DAO.IBookDAO;
import com.booksotre.DAO.IOrderDAO;
import com.booksotre.DAO.IOrderDetailDAO;
import com.booksotre.DAO.impl.BookDAO;
import com.booksotre.DAO.impl.OrderDAO;
import com.booksotre.DAO.impl.OrderDetailDAO;
import com.booksotre.model.OrdersModel;
import com.booksotre.service.IOrderService;

public class OrderService implements IOrderService {
    private final IOrderDAO orderDAO = new OrderDAO();
    private final IBookDAO bookDAO = new BookDAO();
    private final IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @Override
    public List<OrdersModel> findAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    public int saveOrder(OrdersModel order) {
        return orderDAO.saveOrder(order);
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
    public int countByStatus(String status, int customerId) {
        return orderDAO.countByStatus(status, customerId);
    }

    @Override
    public void confirmOrder(int orderId) {
        orderDAO.setStatusOrder(orderId, "Đang giao");
    }

    @Override
    public void cancelOrder(int orderId) {
        orderDetailDAO.findByOrderId(orderId).forEach(o -> bookDAO.setQuantityBook(o.getQuantity(), o.getBookId()));
        orderDetailDAO.deleteByOrderId(orderId);
        orderDAO.setStatusOrder(orderId, "Đã hủy");
    }
}
