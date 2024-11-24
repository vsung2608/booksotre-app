package com.booksotre.controller.user;

import com.booksotre.model.BookModel;
import com.booksotre.model.CartItemModel;
import com.booksotre.model.CartModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.impl.BookService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javax.smartcardio.Card;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartItemController implements Initializable {

    @FXML
    private Label author;

    @FXML
    private TextArea description;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label price;

    @FXML
    private Label publisher;

    @FXML
    private Label quantity;

    @FXML
    private Label totalPrice;

    @FXML
    private AnchorPane product;

    @FXML
    private HBox hboxchild;

    @FXML
    private HBox hbox;

    private final IBookService bookService = new BookService();

    public void setData(CartItemModel cart){
        HBox.setHgrow(hboxchild, Priority.ALWAYS);
        HBox.setHgrow(product, Priority.ALWAYS);

        hbox.setMaxWidth(Double.MAX_VALUE);
        hboxchild.setMaxWidth(Double.MAX_VALUE);

        number.setText(cart.getCartId().toString());
        quantity.setText(cart.getQuantity().toString());
        totalPrice.setText(cart.getPrice().toString());
        BookModel book = bookService.findById(cart.getBookId());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());
        name.setText(book.getTitle());
        publisher.setText(book.getPublisher());
        price.setText(book.getPrice().toString());

        if(book.getImage() != null){
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImage())), 120, 180, false, true);
            image.setImage(img);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
