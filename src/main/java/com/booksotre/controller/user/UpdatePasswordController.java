package com.booksotre.controller.user;

import com.booksotre.model.OrderTamp;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePasswordController implements Initializable {

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    private final ICustomerService customerService = new CustomerService();

    public void updatePassword(){
        Alert alert;
        if (newPassword.getText().isEmpty() || oldPassword.getText().isEmpty() || confirmNewPassword.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            if (customerService.passwordValid(OrderTamp.emailCustomer, oldPassword.getText())) {
                if (newPassword.getText().length() < 8) {
                    alert = AlertUnit.generateAlert(AlertInfo.PASSWORD_INVALID);
                } else if (!newPassword.getText().equals(confirmNewPassword.getText())) {
                    alert = AlertUnit.generateAlert(AlertInfo.CONFIRMNEWPASS_INCORRECT);
                } else {
                    customerService.changePassword(OrderTamp.emailCustomer, newPassword.getText());
                    alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);
                }
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.OLD_PASSWORD_INCORRECT);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
