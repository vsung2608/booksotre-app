package com.booksotre.service;

import com.booksotre.model.BookModel;
import com.booksotre.model.CustomerModel;

import java.util.List;

public interface ICustomerService {
    boolean checkAccountExist(String email);

    void createAccount(CustomerModel customer);

    boolean passwordValid(String email, String password);

    CustomerModel getCustomerByEmail(String email);

    CustomerModel getCustomerByPhone(String phone);

    void updateCustomer(CustomerModel customer);

    void changePassword(String email, String newPassword);

    List<CustomerModel> getAllCustomers();

    void deleteCustomer(int id);

    List<CustomerModel> findCustomerByName(String name);

    List<Integer> getPurchaseInformation(int id);

    List<BookModel> getPurchaseHistory(int customerId);
}
