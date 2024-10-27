package com.booksotre.controller.admin;

import com.booksotre.model.BookModel;
import com.booksotre.model.OrderDetailModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.IBookService;
import com.booksotre.service.impl.BookService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField mn_maKH;

    @FXML
    private TextField mn_soLuongSP;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<OrderDetailModel> tableOrder;

    @FXML
    private ScrollPane thanhCuonSP;

    @FXML
    private Label totalPriceOrder;

    private final IBookService bookService = new BookService();

    private ObservableList<BookModel> listDataBook;

    public void setListDataBook(){
        listDataBook = FXCollections.observableArrayList(bookService.findAll());
    }

    void setListMenu(){
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

                    if (col == 3) {
                        col = 0;
                        row += 1;
                    }
                    containBook.add(pane, col++, row);
                    GridPane.setMargin(pane, new Insets(10));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getTotalOrder() {
        double tol = 0.0;
        for (OrderDetailModel o : OrderTamp.listDetail) {
            tol += o.getQuantity() * o.getPrice();
        }
        totalPriceOrder.setText(String.valueOf(tol));
    }

    public void nutThanhToan() {
    }

    public void nutXoaTTDonHang() {
    }

    public void searchProduct(){
        if(searchTextField.getText().isEmpty()){
            Alert alert = AlertUnit.generateAlert(AlertInfo.ENTER_KEYWORD);
        }else{
            listDataBook = FXCollections.observableArrayList(bookService.findByTitle(searchTextField.getText()));
            setListMenu();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListDataBook();
        setListMenu();
        getTotalOrder();
    }
}
