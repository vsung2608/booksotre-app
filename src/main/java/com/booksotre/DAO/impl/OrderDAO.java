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
                SELECT dt.order_day,
                    COALESCE(COUNT(o.order_id), 0) AS total_orders
                """);
        if (from == null || to == null) {
            query.append(
                    """
                    FROM (
                         SELECT CURDATE() - INTERVAL 6 DAY AS order_day
                         UNION
                         SELECT CURDATE() - INTERVAL 5 DAY
                         UNION
                         SELECT CURDATE() - INTERVAL 4 DAY
                         UNION
                         SELECT CURDATE() - INTERVAL 3 DAY
                         UNION
                         SELECT CURDATE() - INTERVAL 2 DAY
                         UNION
                         SELECT CURDATE() - INTERVAL 1 DAY
                         UNION
                         SELECT CURDATE() AS order_day
                    ) dt
                         LEFT JOIN Orders o ON DATE(o.create_at) = dt.order_day
                    AND o.order_status = ?
                    GROUP BY dt.order_day
                    ORDER BY dt.order_day;
                    """);
            map = countByDate(query.toString(), status);
        } else {
            String set = """
					SET @start_date = ?;
					SET @end_date = ?;
					""";
            query.insert(0, set);
            query.append(
                    """
                    FROM (
                         SELECT @start_date AS order_day
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 1 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 2 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 3 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 4 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 5 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 6 DAY)
                         UNION
                         SELECT DATE_ADD(@start_date, INTERVAL 7 DAY) -- Tính đến end_date
                    ) dt
                         LEFT JOIN Orders o ON DATE(o.create_at) = dt.order_day
                    AND o.order_status = ?
                    WHERE dt.order_day BETWEEN @start_date AND @end_date
                    GROUP BY dt.order_day
                    ORDER BY dt.order_day;
                    """);
            System.out.println(query);
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
