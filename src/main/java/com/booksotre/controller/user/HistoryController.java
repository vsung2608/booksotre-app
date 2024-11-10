package com.booksotre.controller.user;

import com.booksotre.model.OrderTamp;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.service.impl.OrderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private Label book_quantity;

    @FXML
    private Label cancel_order;

    @FXML
    private VBox contain_book_bought;

    @FXML
    private Label cost_analyst;

    @FXML
    private Label process_order;

    @FXML
    private ScrollPane scroll_book_bought;

    @FXML
    private Label success_order;


    private final ICustomerService customerService = new CustomerService();

    private final IOrderService orderService = new OrderService();

    public void setData(){
        List<Integer> list = customerService.getPurchaseInformation(OrderTamp.customerId);
        book_quantity.setText(String.valueOf(list.get(0)));
        cost_analyst.setText(String.valueOf(list.get(1)));

        process_order.setText(String.valueOf(orderService.countByStatus("Chờ xử lý", OrderTamp.customerId)));
        cancel_order.setText(String.valueOf(orderService.countByStatus("Đã hủy", OrderTamp.customerId)));
        success_order.setText(String.valueOf(orderService.countByStatus("Hoàn thành", OrderTamp.customerId)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
