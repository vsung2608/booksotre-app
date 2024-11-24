package com.booksotre.controller.user;
import com.booksotre.model.BookModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICategoryService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CategoryService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
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

    private final IBookService bookService = new BookService();
    private final ICategoryService categoryService = new CategoryService();

    public void setData(int id){
        BookModel book = bookService.findById(id);

        if(book != null){
            upDownQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
            quantitySpinner.setValueFactory(upDownQuantity);

            quantitySpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
                total.setText(newValue * Double.parseDouble(book.getPrice().toString()) + " VNĐ");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
