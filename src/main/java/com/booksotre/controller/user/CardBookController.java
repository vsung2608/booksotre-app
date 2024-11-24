package com.booksotre.controller.user;

import com.booksotre.controller.admin.ContainerController;
import com.booksotre.model.BookModel;
import com.booksotre.model.CartItemModel;
import com.booksotre.model.OrderTamp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CardBookController implements Initializable {

    private BookController homeController;

    @FXML
    private Label author;

    @FXML
    private Label bookName;

    @FXML
    private HBox hopChua;

    @FXML
    private ImageView image;

    @FXML
    private Label price;

    private BookModel book;

    private String[] colors = new String[]{"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void setBookController(BookController homeController) {
        this.homeController = homeController;
    }

    public void setData(BookModel book) {
        this.book = book;
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImage())), 150, 210, false, true);
        image.setImage(img);
        this.hopChua.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";"
                + "-fx-background-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        bookName.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        author.setText(book.getAuthor());
    }

    public void setDataNotColor(BookModel book) {
        this.book = book;
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImage())), 150, 210, false, true);
        image.setImage(img);
        bookName.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        author.setText(book.getAuthor());
    }

    public void showDetail(){
        OrderTamp.bookId = book.getBookId();
        if (homeController != null && homeController.getUserController() != null) {
            homeController.getUserController().showBookDetail(book.getBookId());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
