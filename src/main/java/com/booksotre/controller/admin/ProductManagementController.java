package com.booksotre.controller.admin;

import com.booksotre.model.BookModel;
import com.booksotre.model.CategoryModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICategoryService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CategoryService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ProductManagementController implements Initializable {

    @FXML
    private TableView<BookModel> listBook;

    @FXML
    private ComboBox<String> category;

    @FXML
    private TextField bookId;

    @FXML
    private TextField bookName;

    @FXML
    private TextField isbn;

    @FXML
    private TextField price;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField quantity;

    @FXML
    private TextArea description;

    @FXML
    private ImageView image;

    @FXML
    private TableColumn<BookModel, String> col_author;

    @FXML
    private TableColumn<BookModel, Integer> col_bookId;

    @FXML
    private TableColumn<BookModel, String> col_bookName;

    @FXML
    private TableColumn<CategoryModel, String> col_category;

    @FXML
    private TableColumn<BookModel, String> col_isbn;

    @FXML
    private TableColumn<BookModel, Double> col_price;

    @FXML
    private TableColumn<BookModel, String> col_publisher;

    @FXML
    private TableColumn<BookModel, Integer> col_quantity;

    @FXML
    private TableColumn<BookModel, String> col_status;

    @FXML
    private TextField searchTextField;

    private final ICategoryService categoryService = new CategoryService();

    private final IBookService bookService = new BookService();

    private List<CategoryModel> typeBook;
    private ObservableList<BookModel> listDataBook;
    private String linkImage = null;
    private Alert alert;
    private File selectedImage;

    public void setCategoryCombobox() {
        List<String> type = new ArrayList<>();
        typeBook = categoryService.findAll();
        typeBook.forEach(cat -> type.add(cat.getCategoryName()));
        ObservableList<String> ds = FXCollections.observableArrayList(type);
        category.setItems(ds);
    }

    public int getIdByNameCategory(String name){
        int id = 0;
        for (CategoryModel cat : typeBook) {
            if (cat.getCategoryName().equals(name)) {
                id = cat.getCategoryId();
                break;
            }
        }
        return id;
    }

    public void setListDataBook(){
        listDataBook = FXCollections.observableArrayList(bookService.findAll());
    }

    public void setListBook() {
        col_bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        col_bookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        listBook.setItems(listDataBook);
    }

    public void getBookToUpdate() {
        BookModel book = listBook.getSelectionModel().getSelectedItem();
        int num = listBook.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        bookId.setText(String.valueOf(book.getBookId()));
        bookName.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        price.setText(String.valueOf(book.getPrice()));
        quantity.setText(String.valueOf(book.getQuantity()));
        description.setText(book.getDescription());
        typeBook.forEach(type -> {
            if(Objects.equals(type.getCategoryId(), book.getCategoryId())){
                category.setValue(type.getCategoryName());
            }
        });

        linkImage = book.getImage();
        if (linkImage != null) {
            Image linkI = new Image(Objects.requireNonNull(getClass().getResourceAsStream(linkImage)), 120, 180, false, true);
            image.setImage(linkI);
        }else{
            image.setImage(null);
        }
    }

    public void addBook() {
        if (bookName.getText().isEmpty()
                || category.getSelectionModel().getSelectedItem() == null
                || author.getText().isEmpty()
                || isbn.getText().isEmpty()
                || price.getText().isEmpty()
                || quantity.getText().isEmpty()
                || description.getText().isEmpty()
                || publisher.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            if (bookService.findOne(isbn.getText()) != null) {
                alert = AlertUnit.generateAlert(AlertInfo.PRODUCT_EXIST);
            } else {
                BookModel book = BookModel.builder()
                        .title(bookName.getText())
                        .isbn(isbn.getText())
                        .author(author.getText())
                        .publisher(publisher.getText())
                        .price(BigDecimal.valueOf(Double.parseDouble(price.getText())))
                        .quantity(Integer.parseInt(quantity.getText()))
                        .description(description.getText())
                        .categoryId(Integer.valueOf(category.getSelectionModel().getSelectedItem()))
                        .image(linkImage)
                        .categoryId(getIdByNameCategory(category.getSelectionModel().getSelectedItem()))
                        .build();

                alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ADD);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    bookService.createBook(book);
                    saveImageIntoProject();
                    alert = AlertUnit.generateAlert(AlertInfo.ADD_BOOK_SUCCESSFUL);

                    setListBook();
                    resetDataBook();
                }else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);

            }
        }
    }

    public void updateBook() {
        if (bookName.getText().isEmpty()
                || author.getText().isEmpty()
                || isbn.getText().isEmpty()
                || price.getText().isEmpty()
                || quantity.getText().isEmpty()
                || description.getText().isEmpty()
                || publisher.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            BookModel book = BookModel.builder()
                    .bookId(Integer.parseInt(bookId.getText()))
                    .title(bookName.getText())
                    .isbn(isbn.getText())
                    .author(author.getText())
                    .publisher(publisher.getText())
                    .price(BigDecimal.valueOf(Double.parseDouble(price.getText())))
                    .quantity(Integer.parseInt(quantity.getText()))
                    .categoryId(getIdByNameCategory(category.getSelectionModel().getSelectedItem()))
                    .description(description.getText())
                    .image(linkImage)
                    .build();

            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_UPDATE);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                bookService.updateBook(book);
                if(selectedImage != null){
                    saveImageIntoProject();
                }
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);

                setListBook();
                resetDataBook();
            } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
        }
    }

    public void deleteBook() {
        if (bookId.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_OBJECT);
        } else {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_DELETE);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                BookModel book = BookModel.builder()
                        .bookId(Integer.parseInt(bookId.getText()))
                        .build();
                bookService.deleteBook(book);
                alert = AlertUnit.generateAlert(AlertInfo.DELETE_SUCCESSFUL);

                setListBook();
                resetDataBook();
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
            }
        }
    }

    public void putImage() {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mở file ảnh", "*png", "*jpg"));

        selectedImage = file.showOpenDialog(null);

        if (selectedImage != null) {
            Image img = new Image(selectedImage.toURI().toString(), 120, 180, false, true);
            image.setImage(img);
            linkImage = "/assets/images/" + selectedImage.getName();
        }
    }

    public void saveImageIntoProject(){
        try {
            File destinationFile = new File("src/main/resources" + linkImage);
            Files.copy(selectedImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetDataBook() {
        bookId.setText("");
        bookName.setText("");
        isbn.setText("");
        author.setText("");
        publisher.setText("");
        price.setText("");
        quantity.setText("");
        description.setText("");
        category.getSelectionModel().clearSelection();
        linkImage = null;
        image.setImage(null);
    }

    public void searchProduct(){
        if(searchTextField.getText().isEmpty()){
            alert = AlertUnit.generateAlert(AlertInfo.ENTER_KEYWORD);
        }else{
            listDataBook = FXCollections.observableArrayList(bookService.findByTitle(searchTextField.getText()));
            setListBook();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListDataBook();
        setListBook();
        setCategoryCombobox();
    }
}
