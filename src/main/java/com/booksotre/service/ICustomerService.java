package com.booksotre.service;

import com.booksotre.model.CustomerModel;

public interface ICustomerService {
    boolean checkAccountExist(String email);

    void createAccount(CustomerModel customer);

    boolean passwordValid(String email, String password);

    CustomerModel getCustomerByEmail(String email);

    void updateCustomer(CustomerModel employee);

    void changePassword(String email, String newPassword);
}
