package com.booksotre.DAO;

import com.booksotre.model.OrdersModel;

import java.util.List;

public interface IOrderDAO {
    List<OrdersModel> findAll();
}
