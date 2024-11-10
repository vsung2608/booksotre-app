package com.booksotre.controller.admin;

import com.booksotre.model.CustomerModel;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.impl.CustomerService;
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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

    @FXML
    private TableColumn<CustomerModel, String> col_address;

    @FXML
    private TableColumn<CustomerModel, String> col_dob;

    @FXML
    private TableColumn<CustomerModel, String> col_email;

    @FXML
    private TableColumn<CustomerModel, String> col_gender;

    @FXML
    private TableColumn<CustomerModel, Integer> col_id;

    @FXML
    private TableColumn<CustomerModel, String> col_name;

    @FXML
    private TableColumn<CustomerModel, String> col_phone;

    @FXML
    private TextField input_address;

    @FXML
    private DatePicker input_dob;

    @FXML
    private TextField input_email;

    @FXML
    private TextField input_gender;

    @FXML
    private TextField input_id;

    @FXML
    private TextField input_name;

    @FXML
    private TextField input_phone;

    @FXML
    private TextField input_password;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView avatar;

    @FXML
    private TableView<CustomerModel> listCustomer;

    private ObservableList<CustomerModel> listDataCustomer;

    private final ICustomerService CustomerService = new CustomerService();

    private String linkImage;

    private Alert alert;

    private File selectedImage;

    public void setListDataCustomer() {
        listDataCustomer = FXCollections.observableArrayList(CustomerService.getAllCustomers());
    }

    public void setListCustomer() {

        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        listCustomer.setItems(listDataCustomer);
    }

    public void getCustomerToUpdate() {
        CustomerModel customer = listCustomer.getSelectionModel().getSelectedItem();
        int num = listCustomer.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        input_address.setText(customer.getAddress());
        input_dob.setValue(customer.getDob());
        input_email.setText(customer.getEmail());
        input_gender.setText(customer.getGender());
        input_id.setText(customer.getCustomerId().toString());
        input_name.setText(customer.getCustomerName());
        input_phone.setText(customer.getPhone());

        linkImage = customer.getAvatar();
        if (linkImage != null) {
            Image linkI =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream(linkImage)), 120, 180, false, true);
            avatar.setImage(linkI);
        } else {
            avatar.setImage(null);
        }
    }

//    public void addCustomer() {
//        if (input_name.getText().isEmpty()
//                || input_address.getText().isEmpty()
//                || input_dob.getText().isEmpty()
//                || input_email.getText().isEmpty()
//                || input_gender.getText().isEmpty()
//                || input_role.getText().isEmpty()
//                || input_phone.getText().isEmpty()
//                || input_salary.getText().isEmpty()) {
//            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
//        } else {
//            if (CustomerService.checkAccountExist()) {
//                alert = AlertUnit.generateAlert(AlertInfo.PRODUCT_EXIST);
//            } else {
//                BookModel book = BookModel.builder()
//                        .title(bookName.getText())
//                        .isbn(isbn.getText())
//                        .author(author.getText())
//                        .publisher(publisher.getText())
//                        .price(BigDecimal.valueOf(Double.parseDouble(price.getText())))
//                        .quantity(Integer.parseInt(quantity.getText()))
//                        .description(description.getText())
//                        .categoryId(Integer.valueOf(category.getSelectionModel().getSelectedItem()))
//                        .image(linkImage)
//                        .categoryId(
//                                getIdByNameCategory(category.getSelectionModel().getSelectedItem()))
//                        .build();
//
//                alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ADD);
//                Optional<ButtonType> option = alert.showAndWait();
//                if (option.get().equals(ButtonType.OK)) {
//                    CustomerService.createBook(book);
//                    saveImageIntoProject();
//                    alert = AlertUnit.generateAlert(AlertInfo.ADD_BOOK_SUCCESSFUL);
//
//                    setlistCustomer();
//                    resetDataBook();
//                } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
//            }
//        }
//    }

    public void updateCustomer() {
        if (input_name.getText().isEmpty()
                || input_address.getText().isEmpty()
                || input_dob.getValue().toString().isEmpty()
                || input_email.getText().isEmpty()
                || input_gender.getText().isEmpty()
                || input_phone.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            CustomerModel customer = CustomerModel.builder()
                    .customerId(Integer.parseInt(input_id.getText()))
                    .email(input_email.getText())
                    .gender(input_gender.getText())
                    .customerName(input_name.getText())
                    .phone(input_phone.getText())
                    .dob(input_dob.getValue())
                    .address(input_address.getText())
                    .avatar(linkImage)
                    .build();

            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_UPDATE);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                CustomerService.updateCustomer(customer);
                if (selectedImage != null) {
                    saveImageIntoProject();
                }
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);

                setListCustomer();
                resetDataBook();
            } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
        }
    }

    public void deleteCustomer() {
        if (input_id.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_OBJECT);
        } else {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_DELETE);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                CustomerService.deleteCustomer(Integer.parseInt(input_id.getText()));
                alert = AlertUnit.generateAlert(AlertInfo.DELETE_SUCCESSFUL);

                setListCustomer();
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
            avatar.setImage(img);
            linkImage = "/assets/images/" + selectedImage.getName();
        }
    }

    public void saveImageIntoProject() {
        try {
            File destinationFile = new File("src/main/resources" + linkImage);
            Files.copy(selectedImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetDataBook() {
        input_id.setText("");
        input_email.setText("");
        input_gender.setText("");
        input_dob.setValue(null);
        input_address.setText("");
        input_phone.setText("");
        input_password.setText("");
        linkImage = null;
        avatar.setImage(null);
    }

    public void searchCustomer() {
        if (searchTextField.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.ENTER_KEYWORD);
        } else {
            listDataCustomer = FXCollections.observableArrayList(CustomerService.findCustomerByName(searchTextField.getText()));
            setListCustomer();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListDataCustomer();
        setListCustomer();
    }
}
