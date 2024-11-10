package com.booksotre.DAO.impl;

import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.DAO.IOrderDAO;
import com.booksotre.mapper.impl.OrderMapper;
import com.booksotre.model.OrdersModel;

public class OrderDAO extends AbstractDAO<OrdersModel> implements IOrderDAO {
    @Override
    public List<OrdersModel> findAll() {
        String query = "SELECT * FROM Orders";
        return query(query, new OrderMapper());
    }

    @Override
    public int saveOrder(OrdersModel order) {
        String query = "INSERT INTO Orders(customer_id, employee_id, total_amout, total_price) VALUES(?,?,?,?)";
        return insert(
                query, order.getCustomerId(), order.getEmployeeId(), order.getTotalAmount(), order.getTotalPrice());
    }

    @Override
    public List<OrdersModel> findByStatus(String status) {
        String query =
                "SELECT order_id, customer_id, employee_id, total_amout, total_price, order_status, create_at FROM Orders WHERE order_status = ?";
        return query(query, new OrderMapper(), status);
    }

    @Override
    public List<OrdersModel> findByDate(String from, String to) {
        StringBuilder query = new StringBuilder();
        List<OrdersModel> list;
        query.append(
                """
				SELECT order_id, customer_id, employee_id, total_amout, total_price, order_status, creat_at
				FROM orders
				""");
        if (from.isEmpty() || to.isEmpty()) {
            query.append(
                    """
					WHERE create_at >= CURDATE() - INTERVAL 7 DAY
						AND create_at < CURDATE();
					""");
            list = query(query.toString(), new OrderMapper());
        } else {
            query.append("""
					WHERE DATE(create_at) BETWEEN ? AND ?;
					""");
            list = query(query.toString(), new OrderMapper(), from, to);
        }
        return list;
    }

    @Override
    public LinkedHashMap<String, Integer> countByDateAndStatus(String from, String to, String status) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        StringBuilder query = new StringBuilder();
        query.append("""
				SELECT DATE(create_at) AS order_day, COUNT(*) AS total_orders
				FROM Orders
				""");
        if (from == null || to == null) {
            query.append(
                    """
					WHERE (create_at >= CURDATE() - INTERVAL 7 DAY
						AND create_at < CURDATE()) AND order_status = ?
						GROUP BY DATE(create_at)
						ORDER BY order_day;
					""");
            map = countByDate(query.toString(), status);
        } else {
            query.append(
                    """
					WHERE (DATE(create_at) BETWEEN ? AND ?) AND order_status = ?
					GROUP BY DATE(create_at)
					ORDER BY order_day;
					""");
            map = countByDate(query.toString(), from, to, status);
        }
        return map;
    }

    @Override
    public int countByStatus(String status, int customerId) {
        String query = "SELECT count(*) FROM Orders WHERE order_status = ?";
        query += (customerId != 0) ? " AND customer_id = ?": " ;";
        if(customerId != 0) return count(query, status, customerId);
        return count(query, status);
    }

    @Override
    public Double getTotalIncome() {
        String query = "SELECT SUM(total_price) AS total FROM Orders";
        return countDouble(query);
    }

    @Override
    public void setStatusOrder(int id, String status) {
        String query = "UPDATE Orders SET order_status = ? WHERE order_id = ?";
        update(query, status, id);
    }
}
