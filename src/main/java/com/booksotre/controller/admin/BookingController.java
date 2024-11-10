package com.booksotre.controller.admin;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import com.booksotre.DAO.impl.AbstractDAO;
import com.booksotre.model.*;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.IOrderDetailService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.service.impl.OrderDetailService;
import com.booksotre.service.impl.OrderService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BookingController implements Initializable {

    @FXML
    private TableColumn<OrderDetailModel, Integer> col_bookID;

    @FXML
    private TableColumn<OrderDetailModel, Double> col_bookPrice;

    @FXML
    private TableColumn<OrderDetailModel, Integer> col_bookQuantity;

    @FXML
    private GridPane containBook;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<OrderDetailModel> tableOrder;

    @FXML
    private Label totalPriceOrder;

    @FXML
    private TextField phoneCusrtomer;

    private final IBookService bookService = new BookService();

    private final ICustomerService customerService = new CustomerService();

    private final IOrderService orderService = new OrderService();

    private final IOrderDetailService orderDetailService = new OrderDetailService();

    private ObservableList<BookModel> listDataBook;

    private Alert alert;

    private double tol;

    public void setListDataBook() {
        listDataBook = FXCollections.observableArrayList(bookService.findAll());
    }

    void setListMenu() {
        int row = 0;
        int col = 0;

        containBook.getRowConstraints().clear();
        containBook.getColumnConstraints().clear();
        for (BookModel bookModel : listDataBook) {
            try {
                if (bookModel.getImage() != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/admin/ContainerFXML.fxml"));
                    AnchorPane pane = loader.load();
                    ContainerController cardController = loader.getController();
                    cardController.setData(bookModel);

                    if (col == 4) {
                        col = 0;
                        row += 1;
                    }
                    containBook.add(pane, col++, row);
                    GridPane.setMargin(pane, new Insets(20));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getTotalOrder() {
        tol = 0.0;
        for (OrderDetailModel o : OrderTamp.listDetail) {
            tol += o.getPrice();
        }
        totalPriceOrder.setText("$" + tol);
    }

    public void payment() {
        if (phoneCusrtomer.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            String phone = phoneCusrtomer.getText();
            CustomerModel customer = customerService.getCustomerByPhone(phone);
            if (customer == null) {
                alert = AlertUnit.generateAlert(AlertInfo.NOT_EXIST_ACCOUNT);
            } else {
                int id = orderService.saveOrder(OrdersModel.builder()
                        .customerId(customer.getCustomerId())
                        .employeeId(OrderTamp.employeeId)
                        .totalPrice(tol)
                        .totalAmount(OrderTamp.listDetail.size())
                        .build());

                OrderTamp.listDetail.forEach(o -> o.setOrderId(id));
                orderDetailService.saveAll(OrderTamp.listDetail);
                OrderTamp.listDetail.forEach(o -> bookService.setQuantity(o.getBookId(), o.getQuantity()));
                OrderTamp.orderId = id;
                alert = AlertUnit.generateAlert(AlertInfo.PAYMENT_SUCCESSFUL);
                deleteOrder();
            }
        }
    }

    public void extractOrder() {
        if (OrderTamp.orderId == null) {
            alert = AlertUnit.generateAlert(AlertInfo.NON_ORDER);
        } else {
            Connection connect = AbstractDAO.getConnection();
            HashMap map = new HashMap();
            map.put("getorderId", OrderTamp.orderId);
            try {
                JasperDesign jasperD = JRXmlLoader.load("D:\\TTCSN\\source\\bookstore\\src\\main\\resources\\template\\report.jrxml");

                JasperReport jReport = JasperCompileManager.compileReport(jasperD);

                JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connect);

                JasperViewer.viewReport(jPrint, false);
            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteOrder() {
        totalPriceOrder.setText("$" + 0.0);
        phoneCusrtomer.setText("");
        OrderTamp.listDetail.clear();
        setListBooking();
    }

    public void setListBooking() {
        ObservableList<OrderDetailModel> listBooking = FXCollections.observableArrayList(OrderTamp.listDetail);

        col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        col_bookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_bookQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableOrder.setItems(listBooking);
    }

    public void searchProduct() {
        if (searchTextField.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.ENTER_KEYWORD);
        } else {
            listDataBook = FXCollections.observableArrayList(bookService.findByTitle(searchTextField.getText()));
            setListMenu();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListDataBook();
        setListMenu();
        getTotalOrder();
        setListBooking();
    }
}
