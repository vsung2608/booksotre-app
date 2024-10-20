package com.booksotre.service.impl;

import com.booksotre.DAO.ICustomerDAO;
import com.booksotre.DAO.impl.CustomerDAO;
import com.booksotre.model.CustomerModel;
import com.booksotre.service.ICustomerService;
import com.booksotre.utils.PasswordUtil;

public class CustomerService implements ICustomerService {

    private final ICustomerDAO customerDAO = new CustomerDAO();

    public boolean checkAccountExist(String email) {
        return customerDAO.checkAccountExistence(email) != 0;
    }

    @Override
    public void createAccount(CustomerModel customer) {
        customer.setPassword(PasswordUtil.hashPassword(customer.getPassword()));
        customerDAO.createAccount(customer);
    }

    @Override
    public boolean passwordValid(String email, String password) {
        CustomerModel customer = customerDAO.getCustomerByEmail(email);
        return PasswordUtil.checkPassword(password, customer.getPassword());
    }

    @Override
    public CustomerModel getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

    @Override
    public void updateCustomer(CustomerModel customer) {
        customerDAO.updateAccount(customer);
    }

    @Override
    public void changePassword(String email, String newPassword) {
        String hashPass = PasswordUtil.hashPassword(newPassword);
        customerDAO.changePassword(email, hashPass);
    }
}
