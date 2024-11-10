package com.booksotre.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.model.OrderDetailModel;

public interface IOrderDetailService {
    LinkedHashMap<String, Integer> getBestSeller();

    void saveAll(List<OrderDetailModel> list);
}
