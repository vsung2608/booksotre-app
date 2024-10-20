package com.booksotre.DAO.impl;

import java.util.List;

import com.booksotre.DAO.ICustomerDAO;
import com.booksotre.mapper.impl.CustomerMapper;
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
				INSERT INTO Customer(email, password, customer_name, gender, dob, phone, address)
				VALUES (?, ?, ?, ?, ?, ?, ?);
				""";
        insert(
                query,
                customer.getEmail(),
                customer.getPassword(),
                customer.getCustomerName(),
                customer.getGender(),
                customer.getDob(),
                customer.getPhone(),
                customer.getAddress());
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
}
