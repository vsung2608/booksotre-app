package com.booksotre.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.booksotre.model.*;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.EmployeeService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

public class AdminController implements Initializable {

    @FXML
    private AnchorPane OrderMnForm;

    @FXML
    private AnchorPane CustomerMnForm;

    @FXML
    private AnchorPane EmployeeMnForm;

    @FXML
    private AnchorPane ProductMnForm;

    @FXML
    private AnchorPane bookingForm;

    @FXML
    private Label adminName;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button nutHangTK;

    @FXML
    private Button OrderMnButton;

    @FXML
    private Button CustomerMnButton;

    @FXML
    private Button EmployeeMnButton;

    @FXML
    private Button nutMenu;

    @FXML
    private Button nutTrangChu;

    @FXML
    private AnchorPane profileAdmin;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private ImageView admin_avatar;

    private final IEmployeeService employeeService = new EmployeeService();

    FXMLLoader loaderPrMn = new FXMLLoader(getClass().getResource("/views/admin/ProductManagementFXML.fxml"));
    FXMLLoader loaderBooking = new FXMLLoader(getClass().getResource("/views/admin/BookingFXMl.fxml"));
    FXMLLoader loaderOdMn = new FXMLLoader(getClass().getResource("/views/admin/OrderManagementFXML.fxml"));
    FXMLLoader loaderCMn = new FXMLLoader(getClass().getResource("/views/admin/CustomerManagementFXML.fxml"));
    FXMLLoader loaderEMn = new FXMLLoader(getClass().getResource("/views/admin/EmployeeManagementFXML.fxml"));

    public void setProfile() {
        EmployeeModel employeeModel = employeeService.getEmployeeByEmail(OrderTamp.emailEmployee);
        adminName.setText(employeeModel.getEmployeeName());
        Image img1;
        if (employeeModel.getAvatar() != null) {
            img1 = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream(employeeModel.getAvatar())),
                    70,
                    70,
                    false,
                    true);
        } else {
            img1 = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/defaultAvatar.jpg")),
                    70,
                    70,
                    false,
                    true);
        }
        admin_avatar.setImage(img1);
        Circle clip1 = new Circle(
                admin_avatar.getFitWidth() / 2, admin_avatar.getFitHeight() / 2, admin_avatar.getFitWidth() / 2);
        admin_avatar.setClip(clip1);
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

                logoutBtn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void navigate(ActionEvent e) {

        if (e.getSource() == nutTrangChu) {
            homeForm.setVisible(true);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(false);
            OrderMnForm.setVisible(false);
            profileAdmin.setVisible(false);
            CustomerMnForm.setVisible(false);
            EmployeeMnForm.setVisible(false);

        } else if (e.getSource() == nutHangTK) {
            homeForm.setVisible(false);
            ProductMnForm.setVisible(true);
            bookingForm.setVisible(false);
            OrderMnForm.setVisible(false);
            profileAdmin.setVisible(false);
            CustomerMnForm.setVisible(false);
            EmployeeMnForm.setVisible(false);

//            ProductManagementController prController = loaderPrMn.getController();
//            prController.setListDataBook();
//            prController.setListBook();
        } else if (e.getSource() == nutMenu) {
            homeForm.setVisible(false);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(true);
            OrderMnForm.setVisible(false);
            profileAdmin.setVisible(false);
            CustomerMnForm.setVisible(false);
            EmployeeMnForm.setVisible(false);

            BookingController bookingController = loaderBooking.getController();
            bookingController.getTotalOrder();
            bookingController.setListDataBook();
            bookingController.setListMenu();
            bookingController.setListBooking();
        } else if (e.getSource() == OrderMnButton) {
            homeForm.setVisible(false);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(false);
            OrderMnForm.setVisible(true);
            profileAdmin.setVisible(false);
            CustomerMnForm.setVisible(false);
            EmployeeMnForm.setVisible(false);

            OrderManagementController orderManagementController = loaderOdMn.getController();
            orderManagementController.setListDataOrder();
            orderManagementController.setListOrder();
        }else if (e.getSource() == CustomerMnButton) {
            homeForm.setVisible(false);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(false);
            OrderMnForm.setVisible(false);
            profileAdmin.setVisible(false);
            CustomerMnForm.setVisible(true);
            EmployeeMnForm.setVisible(false);

//            CustomerManagementController controller = loaderCMn.getController();
        }else if (e.getSource() == EmployeeMnButton) {
            if(OrderTamp.role.equals("Quản trị viên")) {
                homeForm.setVisible(false);
                ProductMnForm.setVisible(false);
                bookingForm.setVisible(false);
                OrderMnForm.setVisible(false);
                profileAdmin.setVisible(false);
                CustomerMnForm.setVisible(false);
                EmployeeMnForm.setVisible(true);

//            EmployeeManagementController controller = loaderEMn.getController();
            }else{
                Alert alert = AlertUnit.generateAlert(AlertInfo.NOT_AUTHORIZATION);
            }

        }
    }

    public void loadHomeAdmin() {
        AnchorPane childPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/HomeFXML.fxml"));
            childPane = loader.load();
            homeForm.getChildren().add(childPane);

            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductManagementAdmin() {
        AnchorPane childPanePrMn = null;
        try {
            childPanePrMn = loaderPrMn.load();
            ProductMnForm.getChildren().add(childPanePrMn);

            AnchorPane.setTopAnchor(childPanePrMn, 0.0);
            AnchorPane.setRightAnchor(childPanePrMn, 0.0);
            AnchorPane.setBottomAnchor(childPanePrMn, 0.0);
            AnchorPane.setLeftAnchor(childPanePrMn, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadOrderManagementAdmin() {
        AnchorPane childPaneOdMn = null;
        try {
            childPaneOdMn = loaderOdMn.load();
            OrderMnForm.getChildren().add(childPaneOdMn);

            AnchorPane.setTopAnchor(childPaneOdMn, 0.0);
            AnchorPane.setRightAnchor(childPaneOdMn, 0.0);
            AnchorPane.setBottomAnchor(childPaneOdMn, 0.0);
            AnchorPane.setLeftAnchor(childPaneOdMn, 0.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomerManagementAdmin() {
        AnchorPane childPaneOdMn = null;
        try {
            childPaneOdMn = loaderCMn.load();
            CustomerMnForm.getChildren().add(childPaneOdMn);

            AnchorPane.setTopAnchor(childPaneOdMn, 0.0);
            AnchorPane.setRightAnchor(childPaneOdMn, 0.0);
            AnchorPane.setBottomAnchor(childPaneOdMn, 0.0);
            AnchorPane.setLeftAnchor(childPaneOdMn, 0.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadEmployeeManagementAdmin() {
        AnchorPane childPaneOdMn = null;
        try {
            childPaneOdMn = loaderEMn.load();
            EmployeeMnForm.getChildren().add(childPaneOdMn);

            AnchorPane.setTopAnchor(childPaneOdMn, 0.0);
            AnchorPane.setRightAnchor(childPaneOdMn, 0.0);
            AnchorPane.setBottomAnchor(childPaneOdMn, 0.0);
            AnchorPane.setLeftAnchor(childPaneOdMn, 0.0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadBookingAdmin() {
        AnchorPane childPane = null;
        try {
            childPane = loaderBooking.load();
            bookingForm.getChildren().add(childPane);

            BookingController controller = loaderBooking.getController();
            controller.setAdminController(this);

            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayProfile() {
        profileAdmin.setVisible(true);
        AnchorPane childPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/ProfileAdminFXML.fxml"));
            childPane = loader.load();
            profileAdmin.getChildren().add(childPane);
            ProfileController childController = loader.getController();
            childController.setParentPane(profileAdmin);

            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadHomeAdmin();
        loadBookingAdmin();
        loadProductManagementAdmin();
        loadOrderManagementAdmin();
        loadCustomerManagementAdmin();
        loadEmployeeManagementAdmin();
        setProfile();
    }
}
