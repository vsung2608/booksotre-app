package com.booksotre.DAO.impl;

import com.booksotre.DAO.IOrderDetailDAO;
import com.booksotre.model.OrderDetailModel;

import java.util.LinkedHashMap;

public class OrderDetailDAO extends AbstractDAO<OrderDetailModel> implements IOrderDetailDAO {
    @Override
    public LinkedHashMap<String, Integer> incomeByDate() {
        String query = """
                SELECT b.book_id, b.title, SUM(oi.quantity) AS total_sold
                FROM Book b
                JOIN Order_detail oi ON b.book_id = oi.book_id
                GROUP BY b.book_id, b.title
                ORDER BY total_sold DESC
                LIMIT 5;
                """;
        return countByDate(query);
    }
}
