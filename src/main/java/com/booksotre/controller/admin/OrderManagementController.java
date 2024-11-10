package com.booksotre.controller.admin;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.booksotre.model.OrdersModel;
import com.booksotre.service.impl.OrderService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

public class OrderManagementController implements Initializable {

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
    private TextField input_createAt;

    @FXML
    private TextField input_customerId;

    @FXML
    private TextField input_empolyeeId;

    @FXML
    private TextField input_orderId;

    @FXML
    private TextField input_quantity;

    @FXML
    private TextField input_status;

    @FXML
    private TextField input_totalPrice;

    @FXML
    private TableView<OrdersModel> listOrder;

    @FXML
    private MenuItem processingbtn;

    @FXML
    private MenuItem waitingBtn;

    @FXML
    private MenuItem finishBtn;

    @FXML
    private MenuItem cancelBtn;

    private Alert alert;

    ObservableList<OrdersModel> listDataOrder;

    private final OrderService orderService = new OrderService();

    public void getOrderToUpdate() {
        OrdersModel order = listOrder.getSelectionModel().getSelectedItem();
        int num = listOrder.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        input_createAt.setText(order.getCreateAt().toString());
        input_customerId.setText(order.getCustomerId().toString());
        input_orderId.setText(order.getOrderId().toString());
        input_empolyeeId.setText(order.getEmployeeId().toString());
        input_quantity.setText(order.getTotalAmount().toString());
        input_totalPrice.setText(order.getTotalPrice().toString());
        input_status.setText(order.getOrderStatus());
    }

    public void setListOrder() {
        col_orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        col_order_customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        col_order_employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        col_order_totalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        col_order_totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        col_order_createAt.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        col_order_status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        listOrder.setItems(listDataOrder);
    }

    public void setListDataOrder() {
        listDataOrder = FXCollections.observableArrayList(orderService.findAllOrders());
    }

    public void confirmOrder() {
        if (input_orderId.getText().isEmpty() || input_quantity.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_OBJECT);
        } else if (input_status.getText().equals("Chờ xử lý")) {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                orderService.confirmOrder(Integer.parseInt(input_orderId.getText()));
                alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ORDER);

                setListDataOrder();
                setListOrder();
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.CANNOT_CONFIRM_ORDER);
            }
        }
    }

    public void cancelOrder() {
        if (input_orderId.getText().isEmpty() || input_quantity.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_OBJECT);
        } else if (input_status.getText().equals("Chờ xử lý")
                || input_status.getText().equals("Đang giao")) {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                orderService.cancelOrder(Integer.parseInt(input_orderId.getText()));
                alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ORDER);

                setListDataOrder();
                setListOrder();
            }
        } else {
            alert = AlertUnit.generateAlert(AlertInfo.CANNOT_CANCEL_ORDER);
        }
    }

    public void setListOrderByStatus(ActionEvent e) {
        if (e.getSource() == processingbtn) {
            listDataOrder = FXCollections.observableArrayList(orderService.findAllOrdersByStatus("Đang giao"));
        } else if (e.getSource() == waitingBtn) {
            listDataOrder = FXCollections.observableArrayList(orderService.findAllOrdersByStatus("Chờ xử lý"));
        } else if (e.getSource() == finishBtn) {
            listDataOrder = FXCollections.observableArrayList(orderService.findAllOrdersByStatus("Hoàn thành"));
        } else if (e.getSource() == cancelBtn) {
            listDataOrder = FXCollections.observableArrayList(orderService.findAllOrdersByStatus("Đã hủy"));
        }
        setListOrder();
    }

    public void updateOrder() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListDataOrder();
        setListOrder();
    }
}
