package com.booksotre.service.impl;

import com.booksotre.DAO.IOrderDetailDAO;
import com.booksotre.DAO.impl.OrderDetailDAO;
import com.booksotre.service.IOrderDetailService;

import java.util.LinkedHashMap;

public class OrderDetailService implements IOrderDetailService {
    private final IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @Override
    public LinkedHashMap<String, Integer> getBestSeller() {
        return orderDetailDAO.incomeByDate();
    }
}
