package com.booksotre.controller.user;

import com.booksotre.model.CustomerModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateInformationController implements Initializable {

    @FXML
    private TextField newAddress;

    @FXML
    private DatePicker newDob;

    @FXML
    private TextField newEmail;

    @FXML
    private TextField newGender;

    @FXML
    private TextField newName;

    @FXML
    private TextField newPhone;

    @FXML
    private TextField olaPhone;

    @FXML
    private TextField oldAddress;

    @FXML
    private DatePicker oldDob;

    @FXML
    private TextField oldEmail;

    @FXML
    private TextField oldGender;

    @FXML
    private TextField oldName;

    private final ICustomerService customerService = new CustomerService();

    public void setUpInformation(){
        CustomerModel customer = customerService.getCustomerByEmail(OrderTamp.emailCustomer);

        oldAddress.setText(customer.getAddress());
        oldDob.setValue(customer.getDob());
        oldEmail.setText(customer.getEmail());
        oldGender.setText(customer.getGender());
        oldName.setText(customer.getCustomerName());
        olaPhone.setText(customer.getPhone());

        newAddress.setText(customer.getAddress());
        newDob.setValue(customer.getDob());
        newEmail.setText(customer.getEmail());
        newGender.setText(customer.getGender());
        newName.setText(customer.getCustomerName());
        newPhone.setText(customer.getPhone());
    }

    public void updateProfile() {
        Alert alert;
        if (newAddress.getText().isEmpty() || newDob.getValue().toString().isEmpty() || newEmail.getText().isEmpty()
                || newEmail.getText().isEmpty() || newGender.getText().isEmpty() || newGender.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else if (!newEmail.getText().equals(oldEmail.getText()) && customerService.checkAccountExist(newEmail.getText())) {
            alert = AlertUnit.generateAlert(AlertInfo.EMAIL_EXISTED);
        } else {
            CustomerModel cus = CustomerModel.builder()
                    .dob(newDob.getValue())
                    .email(newEmail.getText())
                    .gender(newGender.getText())
                    .customerId(OrderTamp.customerId)
                    .address(newAddress.getText())
                    .customerName(newName.getText())
                    .phone(newPhone.getText())
                    .build();

            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_UPDATE);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                customerService.updateCustomer(cus);
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);
            } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpInformation();
    }
}

