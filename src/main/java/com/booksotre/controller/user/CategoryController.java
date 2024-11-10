package com.booksotre.controller.user;

import com.booksotre.model.BookModel;
import com.booksotre.model.CategoryModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICategoryService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CategoryService;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private Label category;

    @FXML
    private GridPane containGridPane;

    @FXML
    private ScrollPane containScrollPane;

    private final IBookService bookService = new BookService();

    private final ICategoryService categoryService = new CategoryService();

    private List<CategoryModel> list;

    private HBox selectedButton = null;

    private final PseudoClass selectedClass = PseudoClass.getPseudoClass("selected");

    public int findCategoryIdByName(String name){
        return list.stream().filter(c -> c.getCategoryName().equals(name)).findFirst().map(CategoryModel::getCategoryId).orElse(0);
    }

    private void handleButtonClick(HBox button) {

        if (selectedButton != null) {
            selectedButton.pseudoClassStateChanged(selectedClass, false);
        }

        button.pseudoClassStateChanged(selectedClass, true);
        selectedButton = button;
    }

    public void setDisplay(String name){
        int id = findCategoryIdByName(name);
        List<BookModel> listBook = bookService.findByCategory(id);
        int col = 0;
        int row = 1;

        containGridPane.getChildren().clear();
        try {
            for (BookModel book : listBook) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/user/CardBookAllFXML.fxml"));
                VBox box2 = loader.load();
                CardBookController c = loader.getController();
                c.setDataNotColor(book);

                if (col == 6) {
                    col = 0;
                    ++row;
                }
                containGridPane.add(box2, col++, row);
                GridPane.setMargin(box2, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = categoryService.findAll();
        setDisplay("Thanh xuân");
        category.setText("Thanh xuân");
    }
}
