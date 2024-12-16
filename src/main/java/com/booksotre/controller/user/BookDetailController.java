package com.booksotre.controller.user;
import com.booksotre.model.BookModel;
import com.booksotre.model.CartItemModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICartService;
import com.booksotre.service.ICategoryService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CartService;
import com.booksotre.service.impl.CategoryService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookDetailController implements Initializable {
    @FXML
    private Label author;

    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private ImageView image;

    @FXML
    private Label isbn;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label publisher;

    @FXML
    private Spinner<Integer> quantitySpinner;

    private SpinnerValueFactory<Integer> upDownQuantity;

    @FXML
    private Label total;

    private BookModel book;
    private Double total_price;
    private int total_amount;

    private final IBookService bookService = new BookService();
    private final ICategoryService categoryService = new CategoryService();
    private final ICartService cartService = new CartService();

    public void setData(int id){
        book = bookService.findById(id);

        if(book != null){
            upDownQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
            quantitySpinner.setValueFactory(upDownQuantity);

            quantitySpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
                total.setText(newValue * Double.parseDouble(book.getPrice().toString()) + " VNĐ");
                total_price = newValue * Double.parseDouble(book.getPrice().toString());
                total_amount = newValue;
            });

            name.setText(book.getTitle());
            author.setText(book.getAuthor());
            publisher.setText(book.getPublisher());
            isbn.setText(book.getIsbn());
            description.setText(book.getDescription());
            description.setWrapText(true);
            price.setText(book.getPrice().toString());

            System.out.println(book.getCategoryId());
            category.setText(categoryService.findById(book.getCategoryId()).getCategoryName());

            String link = null;
            try{
                link = book.getImage();
                if(link != null){
                    Image img = new Image(getClass().getResourceAsStream(link), 324, 500, false, true);
                    image.setImage(img);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addBtn(){
        CartItemModel item = CartItemModel.builder()
                .cartId(OrderTamp.cartId)
                .bookId(this.book.getBookId())
                .quantity(upDownQuantity.getValue())
                .price(total_price)
                .build();
        Alert alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ADD_ITEM);
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            cartService.addCartItem(item);
            cartService.updateCart(upDownQuantity.getValue(), total_price, OrderTamp.cartId);
            alert = AlertUnit.generateAlert(AlertInfo.ADD_ITEM_SUCCESS);
            upDownQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
            quantitySpinner.setValueFactory(upDownQuantity);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
