package com.booksotre.DAO.impl;

import com.booksotre.DAO.IOrderDAO;
import com.booksotre.mapper.impl.OrderMapper;
import com.booksotre.model.OrdersModel;

import java.util.List;

public class OrderDAO extends AbstractDAO<OrdersModel> implements IOrderDAO {
    @Override
    public List<OrdersModel> findAll() {
        String query = "SELECT * FROM Orders";
        return query(query, new OrderMapper());
    }
}
