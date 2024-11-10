package com.booksotre.controller.user;

import com.booksotre.model.BookModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.impl.BookService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private ScrollPane containAllBook;

    @FXML
    private HBox containBook;

    @FXML
    private GridPane containChildBook;

    private final IBookService bookService = new BookService();

    public void setDisplay(){
        List<BookModel> listBook = bookService.getTopBook();
        List<BookModel> listAllBook = bookService.findAll();
        int col = 0;
        int row = 1;

        containChildBook.getChildren().clear();
        containBook.getChildren().clear();
        try {
            for (BookModel book : listBook) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/user/CartBookFXML.fxml"));
                HBox box = loader.load();
                CardBookController c = loader.getController();
                c.setData(book);
                containBook.getChildren().add(box);
            }

            for (BookModel book : listAllBook) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/user/CardBookAllFXML.fxml"));
                VBox box2 = loader.load();
                CardBookController c = loader.getController();
                c.setDataNotColor(book);

                if (col == 6) {
                    col = 0;
                    ++row;
                }
                containChildBook.add(box2, col++, row);
                GridPane.setMargin(box2, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisplay();
    }
}
