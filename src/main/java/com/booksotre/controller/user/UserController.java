package com.booksotre.controller.user;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import com.booksotre.model.BookModel;
import com.booksotre.model.CartModel;
import com.booksotre.model.NotificationModel;
import javafx.stage.Stage;

public class UserController implements Initializable {

    @FXML
    private HBox cartButton;

    @FXML
    private AnchorPane cartForm;

    @FXML
    private HBox categoryButton;

    @FXML
    private AnchorPane categoryForm;

    @FXML
    private HBox changePasswordButton;

    @FXML
    private HBox historyButton;

    @FXML
    private AnchorPane historyForm;

    @FXML
    private HBox hotBookButton;

    @FXML
    private HBox logoutButton;

    @FXML
    private AnchorPane hotBookForm;

    @FXML
    private HBox notificationButton;

    @FXML
    private AnchorPane notificationForm;

    @FXML
    private StackPane passwordForm;

    @FXML
    private HBox profileButton;

    @FXML
    private StackPane profileForm;

    @FXML
    private FontAwesomeIcon searchButton;

    @FXML
    private TextField searchTextField;

    private HBox selectedButton = null;

    private final FXMLLoader loaderBook = new FXMLLoader(getClass().getResource("/views/user/BookFXML.fxml"));
    private final FXMLLoader loaderInformation = new FXMLLoader(getClass().getResource("/views/user/UpdateInformationFXML.fxml"));
    private final FXMLLoader loaderPassword = new FXMLLoader(getClass().getResource("/views/user/UpdatePasswordFXML.fxml"));
    private final FXMLLoader loaderCart = new FXMLLoader(getClass().getResource("/views/user/CartFXML.fxml"));
    private final FXMLLoader loaderCategory = new FXMLLoader(getClass().getResource("/views/user/CategoryFXML.fxml"));
    private final FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("/views/user/HistoryFXML.fxml"));

    private final PseudoClass selectedClass = PseudoClass.getPseudoClass("selected");

    private void handleButtonClick(HBox button) {

        if (selectedButton != null) {
            selectedButton.pseudoClassStateChanged(selectedClass, false);
        }

        button.pseudoClassStateChanged(selectedClass, true);
        selectedButton = button;
    }

    public void loadInformationPage(){
        AnchorPane child = null;

        try {
            child = loaderInformation.load();
            profileForm.getChildren().add(child);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPasswordPage(){
        AnchorPane child = null;

        try {
            child = loaderPassword.load();
            passwordForm.getChildren().add(child);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPage(FXMLLoader loader, AnchorPane form){
        AnchorPane child = null;
        try {
            child = loader.load();
            form.getChildren().add(child);

            AnchorPane.setTopAnchor(child, 0.0);
            AnchorPane.setRightAnchor(child, 0.0);
            AnchorPane.setBottomAnchor(child, 0.0);
            AnchorPane.setLeftAnchor(child, 0.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut() {
        Alert alert;
        try {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_LOGOUT);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                AlertUnit.generateAlert(AlertInfo.LOGOUT_SUCCESSFUL);
                Parent root =
                        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin/LoginFXML.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Hệ thống quản lý cửa hàng bán sách");
                stage.setMinHeight(400);
                stage.setWidth(600);
                stage.setScene(scene);
                stage.show();

                logoutButton.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigate(MouseEvent event){
        if(event.getSource() == hotBookButton){
            hotBookForm.setVisible(true);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

            handleButtonClick(hotBookButton);
        }else if(event.getSource() == categoryButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(true);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

            handleButtonClick(categoryButton);
        }else if(event.getSource() == changePasswordButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(true);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

            handleButtonClick(changePasswordButton);
        }else if(event.getSource() == searchButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

        }else if(event.getSource() == profileButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(true);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

            handleButtonClick(profileButton);
            UpdateInformationController c = loaderInformation.getController();
            c.setUpInformation();
        }else if(event.getSource() == notificationButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(true);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(false);

            handleButtonClick(notificationButton);
        }else if(event.getSource() == cartButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(false);
            cartForm.setVisible(true);

            handleButtonClick(cartButton);
        }else if(event.getSource() == historyButton){
            hotBookForm.setVisible(false);
            notificationForm.setVisible(false);
            passwordForm.setVisible(false);
            profileForm.setVisible(false);
            passwordForm.setVisible(false);
            categoryForm.setVisible(false);
            historyForm.setVisible(true);
            cartForm.setVisible(false);

            handleButtonClick(historyButton);
        }else if(event.getSource() == logoutButton){
            logOut();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleButtonClick(hotBookButton);
        loadInformationPage();
        loadPasswordPage();
        loadPage(loaderBook, hotBookForm);
        loadPage(loaderCart, cartForm);
        loadPage(loaderHistory, historyForm);
        loadPage(loaderCategory, categoryForm);
    }
}
