package com.booksotre.DAO.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.DAO.IOrderDetailDAO;
import com.booksotre.mapper.impl.OrderDetailMapper;
import com.booksotre.model.OrderDetailModel;

public class OrderDetailDAO extends AbstractDAO<OrderDetailModel> implements IOrderDetailDAO {
    @Override
    public LinkedHashMap<String, Integer> incomeByDate() {
        String query =
                """
				SELECT b.title, SUM(oi.quantity) AS total_sold
				FROM Book b
				JOIN Order_detail oi ON b.book_id = oi.book_id
				GROUP BY b.book_id, b.title
				ORDER BY total_sold DESC
				LIMIT 5;
				""";
        return countByDate(query);
    }

    @Override
    public List<OrderDetailModel> findByOrderId(int orderId) {
        String query =
                """
				SELECT order_detail_id, order_id, book_id, quantity, price
				FROM Order_detail
				WHERE Order_id = ?;
				""";
        return query(query, new OrderDetailMapper(), orderId);
    }

    @Override
    public void deleteByOrderId(int orderId) {
        String query = "DELETE FROM Order_detail WHERE Order_id = ?;";
        delete(query, orderId);
    }

    @Override
    public void saveListData(List<OrderDetailModel> list) {
        String query =
                """
				INSERT INTO Order_detail (book_id, order_id, quantity, price)
				VALUES (?, ?, ?, ?)
				"""
                        + ", (?, ?, ?, ?)".repeat(list.size() - 1) + ";";

        List<Object> listData = new ArrayList<>();
        for (OrderDetailModel orderDetailModel : list) {
            listData.add(orderDetailModel.getBookId());
            listData.add(orderDetailModel.getOrderId());
            listData.add(orderDetailModel.getQuantity());
            listData.add(orderDetailModel.getPrice());
        }

        listData.forEach(o -> System.out.println(o.getClass().getSimpleName()));

        insertList(query, listData);
    }
}
