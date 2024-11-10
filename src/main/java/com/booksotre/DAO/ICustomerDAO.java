package com.booksotre.DAO;

import com.booksotre.model.BookModel;
import com.booksotre.model.CustomerModel;

import java.util.List;

public interface ICustomerDAO {
    int checkAccountExistence(String email);

    void createAccount(CustomerModel employee);

    CustomerModel getCustomerByEmail(String email);

    CustomerModel getCustomerByPhone(String phone);

    void updateAccount(CustomerModel employee);

    void changePassword(String email, String newPassword);

    List<CustomerModel> getAllCustomers();

    void deleteCustomer(int id);

    List<CustomerModel> getCustomerByName(String name);

    List<Integer> getPurchase(int id);
}
