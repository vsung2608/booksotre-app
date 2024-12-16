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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @Setter
    @Getter
    private UserController userController;

    @FXML
    private HBox adventureButton;

    @FXML
    private Label category;

    @FXML
    private GridPane containGridPane;

    @FXML
    private ScrollPane containScrollPane;

    @FXML
    private HBox educationButton;

    @FXML
    private HBox fantasyButton;

    @FXML
    private HBox fictionButton;

    @FXML
    private HBox horrorButton;

    @FXML
    private HBox psychologyButton;

    @FXML
    private HBox romanceButton;

    @FXML
    private HBox scienceButton;

    @FXML
    private HBox selfButton;

    @FXML
    private HBox youthButton;


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
                c.setCategoryController(this);
                c.setDataNotColor(book);

                if (col == 5) {
                    col = 0;
                    ++row;
                }
                containGridPane.add(box2, col++, row);
                GridPane.setMargin(box2, new Insets(15));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void navigate(MouseEvent event){
        if(event.getSource() == adventureButton){
            handleButtonClick(adventureButton);
            setDisplay("Phiêu lưu ly kỳ");
            category.setText("Phiêu lưu ly kỳ");
        }else if(event.getSource() == educationButton){
            handleButtonClick(educationButton);
            setDisplay("Giáo Dục");
            category.setText("Giáo Dục");
        }else if(event.getSource() == fictionButton){
            handleButtonClick(fictionButton);
            setDisplay("Tiểu Thuyết");
            category.setText("Tiểu Thuyết");
        }else if(event.getSource() == romanceButton){
            handleButtonClick(romanceButton);
            setDisplay("Lãng mạng");
            category.setText("Lãng mạng");
        }else if(event.getSource() == selfButton){
            handleButtonClick(selfButton);
            setDisplay("Phát Triển Bản Thân");
            category.setText("Phát Triển Bản Thân");
        }else if(event.getSource() == fantasyButton){
            handleButtonClick(fantasyButton);
            setDisplay("Kỳ ảo");
            category.setText("Kỳ ảo");
        }else if(event.getSource() == horrorButton){
            handleButtonClick(horrorButton);
            setDisplay("Trinh thám - kinh dị");
            category.setText("Trinh thám - kinh dị");
        }else if(event.getSource() == psychologyButton){
            handleButtonClick(psychologyButton);
            setDisplay("Tâm Lý Học");
            category.setText("Tâm Lý Học");
        }else if(event.getSource() == scienceButton){
            handleButtonClick(scienceButton);
            setDisplay("Khoa Học Viễn Tưởng");
            category.setText("Khoa Học Viễn Tưởng");
        }else if(event.getSource() == youthButton){
            handleButtonClick(youthButton);
            setDisplay("Thanh xuân");
            category.setText("Thanh xuân");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = categoryService.findAll();
        setDisplay("Tiểu Thuyết");
        category.setText("Tiểu Thuyết");
    }
}
