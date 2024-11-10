package com.booksotre.DAO.impl;

import java.util.List;

import com.booksotre.DAO.ICustomerDAO;
import com.booksotre.mapper.impl.CustomerMapper;
import com.booksotre.model.BookModel;
import com.booksotre.model.CustomerModel;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO {
    @Override
    public int checkAccountExistence(String email) {
        String query = "SELECT COUNT(*) FROM Customer WHERE email = ?;";
        return count(query, email);
    }

    @Override
    public void createAccount(CustomerModel customer) {
        String query =
                """
				INSERT INTO Customer(email, password, customer_name, gender, dob, phone, address, avatar)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?);
				""";
        insert(
                query,
                customer.getEmail(),
                customer.getPassword(),
                customer.getCustomerName(),
                customer.getGender(),
                customer.getDob(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getAvatar());
    }

    @Override
    public CustomerModel getCustomerByEmail(String email) {
        String query =
                """
				SELECT customer_id, email, password, customer_name, gender, dob, phone, address, create_at
				FROM Customer
				WHERE email = ?;
				""";
        List<CustomerModel> list = query(query, new CustomerMapper(), email);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public CustomerModel getCustomerByPhone(String phone) {
        String query =
                """
				SELECT customer_id, email, password, customer_name, gender, dob, phone, address, create_at
				FROM Customer
				WHERE phone = ?;
				""";
        List<CustomerModel> list = query(query, new CustomerMapper(), phone);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public void updateAccount(CustomerModel customer) {
        String query =
                """
				UPDATE Customer SET email = ?, customer_name = ?, gender = ?, dob = ?, phone = ?, address = ?
				WHERE customer_id = ?;
				""";
        update(
                query,
                customer.getEmail(),
                customer.getCustomerName(),
                customer.getGender(),
                customer.getDob(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCustomerId());
    }

    @Override
    public void changePassword(String email, String newPassword) {
        String query = """
				UPDATE Customer SET password = ? WHERE email = ?;
				""";
        update(query, newPassword, email);
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        String query = "SELECT customer_id, email, password, customer_name, gender, dob, phone, address, create_at FROM Customer";
        return query(query, new CustomerMapper());
    }

    @Override
    public void deleteCustomer(int id) {
        String query = "DELETE FROM Customer WHERE customer_id = ?;";
        delete(query, id);
    }

    @Override
    public List<CustomerModel> getCustomerByName(String name) {
        String query = "SELECT customer_id, email, password, customer_name, gender, dob, phone, address, create_at FROM Customer WHERE customer_name = ?;";
        return query(query, new CustomerMapper(), "%" + name + "%");
    }

    @Override
    public List<Integer> getPurchase(int id) {
        String query = """
                SELECT SUM(total_amout) amount, SUM(total_price) price
                FROM Orders
                WHERE MONTH(create_at) = MONTH(CURRENT_DATE)
                  AND YEAR(create_at) = YEAR(CURRENT_DATE)
                  AND customer_id = ?;
                """;
        return countByDateList(query, id);
    }
}
