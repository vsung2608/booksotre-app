package com.booksotre.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.booksotre.model.EmployeeModel;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.booksotre.model.CustomerModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.service.impl.EmployeeService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

/**
 * @author PC
 */
public class LoginController implements Initializable {

    @FXML
    private Button fb_backBtn;

    @FXML
    private Button fp_backBtn2;

    @FXML
    private Button fp_changeBtn;

    @FXML
    private AnchorPane fp_changePassword;

    @FXML
    private Button fp_comfirmBtn;

    @FXML
    private ComboBox<?> fp_dob;

    @FXML
    private TextField fp_fullname;

    @FXML
    private PasswordField fp_newPass;

    @FXML
    private PasswordField fp_oldPass;

    @FXML
    private AnchorPane fp_questionForm;

    @FXML
    private TextField fp_username;

    @FXML
    private Hyperlink lg_forgotPass;

    @FXML
    private AnchorPane lg_loginForm;

    @FXML
    private Button lg_loginbtn;

    @FXML
    private PasswordField lg_password;

    @FXML
    private TextField lg_email;

    @FXML
    private TextField rg_address;

    @FXML
    private DatePicker rg_dob;

    @FXML
    private TextField rg_fullName;

    @FXML
    private ComboBox<?> rg_gender;

    @FXML
    private PasswordField rg_password;

    @FXML
    private TextField rg_phone;

    @FXML
    private Button rg_registerBtn;

    @FXML
    private AnchorPane rg_registerForm;

    @FXML
    private TextField rg_email;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private Button side_createBtn;

    @FXML
    private AnchorPane side_form;

    private final ICustomerService customerService = new CustomerService();
    private final IEmployeeService employeeService = new EmployeeService();

    private final List<String> gender = new ArrayList<>();
    private final List<String> dob = new ArrayList<>();
    private Alert alert;

    public void createAccount() {
        if (rg_fullName.getText().isEmpty()
                || rg_phone.getText().isEmpty()
                || rg_address.getText().isEmpty()
                || rg_gender.getSelectionModel().getSelectedItem() == null
                || rg_password.getText().isEmpty()
                || rg_dob.getValue().toString().isEmpty()
                || rg_email.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            if (customerService.checkAccountExist(rg_email.getText())
                    || employeeService.checkAccountExist(rg_email.getText())) {
                alert = AlertUnit.generateAlert(AlertInfo.EMAIL_EXISTED);
            } else if (rg_password.getText().length() < 8) {
                alert = AlertUnit.generateAlert(AlertInfo.PASSWORD_INVALID);
            } else {
                CustomerModel customer = CustomerModel.builder()
                        .email(rg_email.getText())
                        .password(rg_password.getText())
                        .customerName(rg_fullName.getText())
                        .phone(rg_phone.getText())
                        .dob(rg_dob.getValue())
                        .address(rg_address.getText())
                        .gender(rg_gender.getSelectionModel().getSelectedItem().toString())
                        .build();

                customerService.createAccount(customer);

                alert = AlertUnit.generateAlert(AlertInfo.CREAT_ACCOUNT_SUCCESS);

                resetRegisterForm();

                TranslateTransition slider = new TranslateTransition();
                slider.setNode(side_form);
                slider.setToX(0);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((ActionEvent e) -> {
                    side_alreadyHave.setVisible(false);
                    side_createBtn.setVisible(true);
                });

                slider.play();
            }
        }
    }

    public void loginAccount() {
        if (lg_password.getText().isEmpty() || lg_email.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            String view = "";
            if (employeeService.checkAccountExist(lg_email.getText())
                    && employeeService.passwordValid(lg_email.getText(), lg_password.getText())) {
                alert = AlertUnit.generateAlert(AlertInfo.LOGIN_SUCCESSFUL);

                EmployeeModel employee = employeeService.getEmployeeByEmail(lg_email.getText());
                OrderTamp.emailEmployee = employee.getEmail();
                OrderTamp.role = employee.getRole();
                view = "/views/admin/AdminFXML.fxml";
            } else if (customerService.checkAccountExist(lg_email.getText())
                    && customerService.passwordValid(lg_email.getText(), lg_password.getText())) {
                alert = AlertUnit.generateAlert(AlertInfo.LOGIN_SUCCESSFUL);

                CustomerModel customer = customerService.getCustomerByEmail(lg_email.getText());
                OrderTamp.emailCustomer = customer.getEmail();
                OrderTamp.customerId = customer.getCustomerId();
                view = "/views/user/UserFXML.fxml";
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.EMAIL_PASSWORD_INVALID);
            }
            if (!view.isEmpty()) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(view)));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Cửa hàng bán sách");
                    stage.setMinHeight(600);
                    stage.setWidth(1300);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lg_loginbtn.getScene().getWindow().hide();
            }
        }
    }

    public void setValue() {
        if (gender.isEmpty()) {
            this.gender.add("Nam");
            this.gender.add("Nu");

            ObservableList genderData = FXCollections.observableArrayList(gender);
            rg_gender.setItems(genderData);
        }
    }

    public void switchForgotPass() {
        setValue();
        fp_questionForm.setVisible(true);
        lg_loginForm.setVisible(false);
    }

    public void forgotPass() {}

    public void changePass() {}

    public void resetRegisterForm() {
        rg_fullName.setText("");
        rg_phone.setText("");
        rg_address.setText("");
        rg_gender.getSelectionModel().clearSelection();
        rg_dob.setValue(null);
        rg_email.setText("");
        rg_password.setText("");
    }

    public void backLoginForm() {
        fp_questionForm.setVisible(false);
        lg_loginForm.setVisible(true);
    }

    public void backQuestionForm() {
        fp_questionForm.setVisible(true);
        fp_changePassword.setVisible(false);
    }

    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();
        setValue();
        if (event.getSource() == side_createBtn) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_createBtn.setVisible(false);

                fp_questionForm.setVisible(false);
                lg_loginForm.setVisible(true);
                fp_changePassword.setVisible(false);
            });

            slider.play();

        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_createBtn.setVisible(true);

                fp_questionForm.setVisible(false);
                lg_loginForm.setVisible(true);
                fp_changePassword.setVisible(false);
            });

            slider.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
