package com.booksotre.controller.user;

import com.booksotre.model.BookModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CardBookController implements Initializable {

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

    private String[] colors = new String[]{"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void setData(BookModel book) {
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
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImage())), 150, 210, false, true);
        image.setImage(img);
        bookName.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        author.setText(book.getAuthor());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
