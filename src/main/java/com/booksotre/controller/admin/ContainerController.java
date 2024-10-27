package com.booksotre.controller.admin;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.booksotre.model.BookModel;
import com.booksotre.model.OrderDetailModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.IBookService;
import com.booksotre.service.impl.BookService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ContainerController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    @FXML
    private Label bookPrice;

    @FXML
    private Spinner<Integer> bookQuantity;

    private BookModel book = new BookModel();

    private Double totalPrice;
    private Image img;
    private SpinnerValueFactory<Integer> upDownQuantity;
    private final IBookService bookService = new BookService();

    public void setData(BookModel book) {
        this.book = book;
        bookName.setText(book.getTitle());
        bookPrice.setText("$" + book.getPrice());
        String link = book.getImage();
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(link)), 150, 210, false, true);
        bookImage.setImage(img);
    }

    public void setQuantity() {
        upDownQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        bookQuantity.setValueFactory(upDownQuantity);
    }

    public void addBtn() {

        BookingController bookContoller = new BookingController();
        Alert alert;
        int quantity = upDownQuantity.getValue();
        try {
            int checkQuantity = book.getQuantity();
            String checkStatus = book.getStatus();
            if (checkQuantity == 0) {
                book.setStatus("Hết hàng");
            }
            if (!checkStatus.equals("Còn hàng") || quantity == 0) {
                alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_QUANTITY);
            } else {
                if (checkQuantity < quantity) {
                    alert = AlertUnit.generateAlert(AlertInfo.NOT_ENOUGH_BOOK);
                    totalPrice = (quantity * Double.parseDouble(String.valueOf(book.getPrice())));
                    OrderDetailModel od = OrderDetailModel.builder()
                            .bookId(book.getBookId())
                            .quantity(quantity)
                            .build();
                    OrderTamp.listDetail.add(od);
                    int setQuantity = checkQuantity - quantity;
                    book.setQuantity(setQuantity);
                    bookService.updateBook(book);
                    alert = AlertUnit.generateAlert(AlertInfo.ADD_BOOK_SUCCESSFUL);
                    bookContoller.getTotalOrder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantity();
    }
}
