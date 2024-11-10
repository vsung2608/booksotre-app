package com.booksotre.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.DAO.IOrderDetailDAO;
import com.booksotre.DAO.impl.OrderDetailDAO;
import com.booksotre.model.OrderDetailModel;
import com.booksotre.service.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService {
    private final IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @Override
    public LinkedHashMap<String, Integer> getBestSeller() {
        return orderDetailDAO.incomeByDate();
    }

    @Override
    public void saveAll(List<OrderDetailModel> list) {
        orderDetailDAO.saveListData(list);
    }
}
