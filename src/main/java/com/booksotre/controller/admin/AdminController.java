package com.booksotre.controller.admin;


import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

import com.booksotre.model.*;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.EmployeeService;
import com.booksotre.service.impl.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

public class AdminController implements Initializable {

    @FXML
    private AnchorPane HoaDonForm;

    @FXML
    private AnchorPane ProductMnForm;

    @FXML
    private TableView<OrdersModel> listOrder;

    @FXML
    private AnchorPane bookingForm;

    @FXML
    private Label adminName;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button nutHangTK;

    @FXML
    private Button nutXDSHoaDon;

    @FXML
    private Button nutMenu;

    @FXML
    private Button nutTrangChu;

    @FXML
    private AnchorPane profileAdmin;

    @FXML
    private AnchorPane trangChuForm;

    @FXML
    private ImageView admin_avatar;

    @FXML
    private TableColumn<OrdersModel, Integer> col_orderId;

    @FXML
    private TableColumn<OrdersModel, Timestamp> col_order_createAt;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_customerId;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_employeeId;

    @FXML
    private TableColumn<OrdersModel, String> col_order_status;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_totalAmount;

    @FXML
    private TableColumn<OrdersModel, Double> col_order_totalPrice;

    @FXML
    private ScrollPane thanhCuonSP;

    @FXML
    private Label h_soLuongKH;

    @FXML
    private Label h_thuNhapTrongNgay;

    @FXML
    private Label h_tongThuNhap;

    @FXML
    private Label h_soLuongDonHang;

    @FXML
    private AreaChart<?, ?> h_bieuDoThuNhap;

    @FXML
    private AreaChart<?, ?> h_bieuDoLuongKH;


    private final IOrderService orderService = new OrderService();

    private final IEmployeeService employeeService = new EmployeeService();

    FXMLLoader loaderPrMn = new FXMLLoader(getClass().getResource("/views/admin/ProductManagementFXML.fxml"));
    FXMLLoader loaderBooking = new FXMLLoader(getClass().getResource("/views/admin/BookingFXMl.fxml"));

    public void setProfile(){
        EmployeeModel employeeModel = employeeService.getEmployeeByEmail(OrderTamp.emailEmployee);
        adminName.setText(employeeModel.getEmployeeName());
        Image img1;
        if(employeeModel.getAvatar() != null){
            img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(employeeModel.getAvatar())), 70, 70, false, true);
        }else{
            img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/defaultAvatar.jpg")), 70, 70, false, true);
        }
        admin_avatar.setImage(img1);
        Circle clip1 = new Circle(admin_avatar.getFitWidth() / 2, admin_avatar.getFitHeight() / 2, admin_avatar.getFitWidth() / 2);
        admin_avatar.setClip(clip1);
    }

    public void setListOrder() {
        ObservableList<OrdersModel> listDataOrder = FXCollections.observableArrayList(orderService.findAllOrders());

        col_orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        col_order_customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        col_order_employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        col_order_totalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        col_order_totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        col_order_createAt.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        col_order_status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        listOrder.setItems(listDataOrder);
    }

    public void hienThiDanhSachHD() {
    }

    public void hienThiBieuDoThuNhap() {
    }

    public void hienThiBieuDoKhachHang() {

    }

    public void logOut() {

        try {
            Alert alert = AlertUnit.generateAlert(AlertInfo.LOGOUT_SUCCESSFUL);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin/LoginFXML.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("BookStore Management System");
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
            trangChuForm.setVisible(true);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(false);
            HoaDonForm.setVisible(false);
            profileAdmin.setVisible(false);

            hienThiBieuDoThuNhap();
            hienThiBieuDoKhachHang();
        } else if (e.getSource() == nutHangTK) {
            trangChuForm.setVisible(false);
            ProductMnForm.setVisible(true);
            bookingForm.setVisible(false);
            HoaDonForm.setVisible(false);
            profileAdmin.setVisible(false);

            ProductManagementController prController = loaderPrMn.getController();
            prController.setListDataBook();
            prController.setListBook();
        } else if (e.getSource() == nutMenu) {
            trangChuForm.setVisible(false);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(true);
            HoaDonForm.setVisible(false);
            profileAdmin.setVisible(false);

            BookingController bookingController = loaderBooking.getController();
            bookingController.getTotalOrder();
            bookingController.setListDataBook();
            bookingController.setListMenu();
            setListOrder();
        } else if (e.getSource() == nutXDSHoaDon) {
            trangChuForm.setVisible(false);
            ProductMnForm.setVisible(false);
            bookingForm.setVisible(false);
            HoaDonForm.setVisible(true);
            profileAdmin.setVisible(false);

            hienThiDanhSachHD();
        }
    }

    public void loadHomeAdmin(){
        AnchorPane childPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/HomeFXML.fxml"));
            childPane = loader.load();
            trangChuForm.getChildren().add(childPane);

            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductManagementAdmin(){
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

    public void loadBookingAdmin(){
        AnchorPane childPane = null;
        try {
            childPane = loaderBooking.load();
            bookingForm.getChildren().add(childPane);

            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayProfile(){
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
        loadProductManagementAdmin();
        setProfile();
        setListOrder();
        hienThiDanhSachHD();
        hienThiBieuDoThuNhap();
        hienThiBieuDoKhachHang();
    }
}
