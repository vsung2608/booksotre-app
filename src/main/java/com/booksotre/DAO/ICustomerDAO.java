package com.booksotre.DAO;

import com.booksotre.model.CustomerModel;

public interface ICustomerDAO {
    int checkAccountExistence(String email);

    void createAccount(CustomerModel employee);

    CustomerModel getCustomerByEmail(String email);

    void updateAccount(CustomerModel employee);

    void changePassword(String email, String newPassword);
}
