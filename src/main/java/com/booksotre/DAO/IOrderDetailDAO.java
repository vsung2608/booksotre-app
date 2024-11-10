package com.booksotre.DAO;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.model.OrderDetailModel;

public interface IOrderDetailDAO {

    LinkedHashMap<String, Integer> incomeByDate();

    List<OrderDetailModel> findByOrderId(int orderId);

    void deleteByOrderId(int orderId);

    void saveListData(List<OrderDetailModel> list);
}
