package com.booksotre.controller;


import java.net.*;
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
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
    private TextField lg_username;

    @FXML
    private TextField rg_address;

    @FXML
    private ComboBox<?> rg_dob;

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
    private TextField rg_username;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private Button side_createBtn;

    @FXML
    private AnchorPane side_form;


    private List<String> gender = new ArrayList<>();
    private List<String> dob = new ArrayList<>();
    private Alert alert;

    public void createAccount() {

    }

    public void loginAccount() {

    }

    public void setValue() {
        if (gender.isEmpty()) {
            for (int i = 1990; i < 2024; i++) {
                this.dob.add(String.valueOf(i));
            }
            this.gender.add("Nam");
            this.gender.add("Nu");

            ObservableList genderData = FXCollections.observableArrayList(gender);
            ObservableList dobData = FXCollections.observableArrayList(dob);
            rg_gender.setItems(genderData);
            rg_dob.setItems(dobData);
            fp_dob.setItems(dobData);
        }
    }

    public void switchForgotPass() {
        setValue();
        fp_questionForm.setVisible(true);
        lg_loginForm.setVisible(false);
    }

    public void forgotPass() {

    }

    public void changePass() {

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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

