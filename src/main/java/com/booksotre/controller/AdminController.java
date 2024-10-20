package com.booksotre.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.*;

import com.booksotre.model.OrdersModel;
import com.booksotre.service.ICategoryService;
import com.booksotre.service.IOrderService;
import com.booksotre.service.impl.CategoryService;
import com.booksotre.service.impl.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.booksotre.model.BookModel;
import com.booksotre.model.CategoryModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.impl.BookService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

public class AdminController implements Initializable {

    @FXML
    private AnchorPane HoaDonForm;

    @FXML
    private AnchorPane QlyHTKForm;

    //    @FXML
    //    private TableView<OrderDetail> bangHoaDon;
    //
    //    @FXML
    //    private GridPane OChuaSP;
    //
    @FXML
    private TableView<BookModel> listBook;

    @FXML
    private TableView<OrdersModel> listOrder;

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
    private TableColumn<OrdersModel, Integer> col_orderId;

    @FXML
    private TableColumn<OrdersModel, Timestamp> col_order_createAt;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_customerId;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_employeeId;

    @FXML
    private TableColumn<OrdersModel, String> col_order_status;

    @FXML
    private TableColumn<OrdersModel, Integer> col_order_totalAmount;

    @FXML
    private TableColumn<OrdersModel, Double> col_order_totalPrice;

    @FXML
    private TextField mn_maKH;

    @FXML
    private AnchorPane giaoDienChinh;

    @FXML
    private AnchorPane menuForm;

    //    @FXML
    //    private TableColumn<OrderDetail, Integer> mn_cotSoLuong;
    //
    //    @FXML
    //    private TableColumn<OrderDetail, Integer> mn_cotGia;
    //
    //    @FXML
    //    private TableColumn<OrderDetail, Integer> mn_cotMaSP;

    @FXML
    private TextField mn_soLuongSP;

    @FXML
    private Button resetButton;

    @FXML
    private Button nutDangXuat;

    @FXML
    private Button putImageButton;

    @FXML
    private Button nutHangTK;

    @FXML
    private Button nutXDSHoaDon;

    @FXML
    private Button nutMenu;

    @FXML
    private Button updateButton;

    @FXML
    private Button nutThanhToan;

    @FXML
    private Button addButton;

    @FXML
    private Button nutTrangChu;

    @FXML
    private Button deleteButton;

    @FXML
    private Button nutXoaHoaDon;

    @FXML
    private Button nutXuatHoaDon;

    @FXML
    private Label tenNguoiDung;

    @FXML
    private Label tongTien;

    @FXML
    private AnchorPane trangChuForm;

    @FXML
    private ScrollPane thanhCuonSP;

    @FXML
    private Label h_soLuongKH;

    @FXML
    private Label h_thuNhapTrongNgay;

    @FXML
    private Label h_tongThuNhap;

    @FXML
    private Label h_soLuongDonHang;

    @FXML
    private AreaChart<?, ?> h_bieuDoThuNhap;

    @FXML
    private AreaChart<?, ?> h_bieuDoLuongKH;

    private final IBookService bookService = new BookService();

    private final ICategoryService categoryService = new CategoryService();

    private final IOrderService orderService = new OrderService();

    private Alert alert;
    private String linkImage = "";

    private ObservableList<BookModel> listDataBook;

    public void setCategoryCombobox() {
        List<String> type = new ArrayList<>();
        categoryService.findAll()
                .forEach(cat -> type.add(cat.getCategoryName()));
        ObservableList<String> ds = FXCollections.observableArrayList(type);
        category.setItems(ds);
    }

    public void setListBook() {
        listDataBook = FXCollections.observableArrayList(bookService.findAll());

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

    public void setListOrder() {
        ObservableList<OrdersModel> listDataOrder = FXCollections.observableArrayList(orderService.findAllOrders());

        col_orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        col_order_customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        col_order_employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        col_order_totalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        col_order_totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        col_order_createAt.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        col_order_status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        listOrder.setItems(listDataOrder);
    }

    public ObservableList<BookModel> layDuLieuSanPham() {
        return null;
    }

    public void hienThiOChuaSanPham() {
    }

    public void hienThiDanhSachHD() {
    }

    public void hienThiTenNguoiDung() {
    }

    public void layTongTienHoaDon() {
    }

    public void hienThiTongTien() {
    }

    public void hienThiTTTrangChu() {
    }

    public void hienThiBieuDoThuNhap() {
    }

    public void hienThiBieuDoKhachHang() {
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

        linkImage = book.getImage();
        if(linkImage != null){
            Image linkI = new Image(linkImage, 120, 180, false, true);
            image.setImage(linkI);
        }
    }

    public void logOut() {

        try {
            alert = AlertUnit.generateAlert(AlertInfo.LOGOUT_SUCCESSFUL);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/LoginFXML.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Cafe Shop System");
                stage.setMinHeight(400);
                stage.setWidth(600);
                stage.setScene(scene);
                stage.show();

                nutDangXuat.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                        .image(linkImage)
                        .build();

                bookService.createBook(book);
                alert = AlertUnit.generateAlert(AlertInfo.ADD_BOOK_SUCCESSFUL);

                setListBook();
                resetDataBook();
            }
        }
    }

    public void updateBook() {
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
            BookModel book = BookModel.builder()
                    .title(bookName.getText())
                    .isbn(isbn.getText())
                    .author(author.getText())
                    .publisher(publisher.getText())
                    .price(BigDecimal.valueOf(Double.parseDouble(price.getText())))
                    .quantity(Integer.parseInt(quantity.getText()))
                    .description(description.getText())
                    .image(linkImage)
                    .build();

            bookService.updateBook(book);
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_UPDATE);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);

                setListBook();
                resetDataBook();
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
            }
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

        File fileI = file.showOpenDialog(giaoDienChinh.getScene().getWindow());

        if (fileI != null) {
            Image img = new Image(fileI.toURI().toString(), 120, 180, false, true);
            image.setImage(img);

            try {
                linkImage = "src/main/resources/assets/images/" + fileI.getName();
                File destinationFile = new File(linkImage);
                Files.copy(fileI.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        linkImage = "";
        image.setImage(null);
    }

    public void nutThanhToan() {
    }

    public void nutXoaTTDonHang() {
    }

    public void changePassword() {
    }

    public void navigate(ActionEvent e) {

        if (e.getSource() == nutTrangChu) {
            trangChuForm.setVisible(true);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            hienThiTTTrangChu();
            hienThiBieuDoThuNhap();
            hienThiBieuDoKhachHang();
        } else if (e.getSource() == nutHangTK) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(true);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(false);

            setCategoryCombobox();
            setListBook();
        } else if (e.getSource() == nutMenu) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(true);
            HoaDonForm.setVisible(false);

            hienThiOChuaSanPham();
            hienThiTongTien();
            setListOrder();
        } else if (e.getSource() == nutXDSHoaDon) {
            trangChuForm.setVisible(false);
            QlyHTKForm.setVisible(false);
            menuForm.setVisible(false);
            HoaDonForm.setVisible(true);

            hienThiDanhSachHD();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hienThiTenNguoiDung();
        setCategoryCombobox();
        setListBook();

        hienThiOChuaSanPham();
        hienThiTongTien();
        setListOrder();
        hienThiDanhSachHD();
        hienThiTTTrangChu();
        hienThiBieuDoThuNhap();
        hienThiBieuDoKhachHang();
    }
}
