package com.booksotre.controller.admin;

import com.booksotre.model.BookModel;
import com.booksotre.model.CustomerModel;
import com.booksotre.model.EmployeeModel;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.EmployeeService;
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

public class EmployeeManagementController implements Initializable {

    @FXML
    private ImageView avatar;

    @FXML
    private TableColumn<EmployeeModel, String> col_address;

    @FXML
    private TableColumn<EmployeeModel, String> col_dob;

    @FXML
    private TableColumn<EmployeeModel, String> col_email;

    @FXML
    private TableColumn<EmployeeModel, String> col_gender;

    @FXML
    private TableColumn<EmployeeModel, Integer> col_id;

    @FXML
    private TableColumn<EmployeeModel, String> col_name;

    @FXML
    private TableColumn<EmployeeModel, String> col_pphone;

    @FXML
    private TableColumn<EmployeeModel, String> col_role;

    @FXML
    private TableColumn<EmployeeModel, Double> col_salary;

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField input_address;

    @FXML
    private TextField input_dob;

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
    private TextField input_role;

    @FXML
    private TextField input_password;

    @FXML
    private TableView<EmployeeModel> listEmployee;

    private ObservableList<EmployeeModel> listDataEmployee;
    
    private final IEmployeeService employeeService = new EmployeeService();

    private String linkImage;

    private Alert alert;

    private File selectedImage;

    public void setListDataEmployee() {
        listDataEmployee = FXCollections.observableArrayList(employeeService.getAllEmployees());
    }

    public void setListEmployee() {
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        col_pphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        listEmployee.setItems(listDataEmployee);
    }

    public void getEmployeeToUpdate() {
        EmployeeModel employee = listEmployee.getSelectionModel().getSelectedItem();
        int num = listEmployee.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        input_address.setText(employee.getAddress());
        input_dob.setText(employee.getDob());
        input_email.setText(employee.getEmail());
        input_gender.setText(employee.getGender());
        input_id.setText(employee.getEmployeeId().toString());
        input_name.setText(employee.getEmployeeName());
        input_phone.setText(employee.getPhone());
        input_role.setText(employee.getRole());

        linkImage = employee.getAvatar();
        if (linkImage != null) {
            Image linkI =
                    new Image(Objects.requireNonNull(getClass().getResourceAsStream(linkImage)), 120, 180, false, true);
            avatar.setImage(linkI);
        } else {
            avatar.setImage(null);
        }
    }

    public void addEmployee() {
        if (input_name.getText().isEmpty()
                || input_address.getText().isEmpty()
                || input_dob.getText().isEmpty()
                || input_email.getText().isEmpty()
                || input_gender.getText().isEmpty()
                || input_role.getText().isEmpty()
                || input_phone.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            if (employeeService.checkAccountExist(input_email.getText())) {
                alert = AlertUnit.generateAlert(AlertInfo.EMAIL_EXISTED);
            } else {
                if(linkImage == null){
                    linkImage = "/assets/images/defaultAvatar.jpg";
                }
                EmployeeModel employee = EmployeeModel.builder()
                        .email(input_email.getText())
                        .gender(input_gender.getText())
                        .phone(input_phone.getText())
                        .role(input_role.getText())
                        .employeeName(input_name.getText())
                        .address(input_address.getText())
                        .dob(input_dob.getText())
                        .password("employee123")
                        .avatar(linkImage)
                        .build();
                alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_ADD);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    employeeService.createAccount(employee);
                    saveImageIntoProject();
                    alert = AlertUnit.generateAlert(AlertInfo.ADD_BOOK_SUCCESSFUL);

                    setListDataEmployee();
                    setListEmployee();
                    resetDataBook();
                } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
            }
        }
    }

    public void updateEmployee() {
        if (input_name.getText().isEmpty()
                || input_address.getText().isEmpty()
                || input_dob.getText().isEmpty()
                || input_email.getText().isEmpty()
                || input_gender.getText().isEmpty()
                || input_role.getText().isEmpty()
                || input_phone.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
        } else {
            EmployeeModel employee = EmployeeModel.builder()
                    .employeeId(Integer.parseInt(input_id.getText()))
                    .email(input_email.getText())
                    .gender(input_gender.getText())
                    .employeeName(input_name.getText())
                    .phone(input_phone.getText())
                    .role(input_role.getText())
                    .dob(input_dob.getText())
                    .address(input_address.getText())
                    .avatar(linkImage)
                    .build();

            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_UPDATE);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                employeeService.updateEmployee(employee);
                if (selectedImage != null) {
                    saveImageIntoProject();
                }
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);

                setListDataEmployee();
                setListEmployee();
                resetDataBook();
            } else alert = AlertUnit.generateAlert(AlertInfo.CANCEL);
        }
    }

    public void deleteEmployee() {
        if (input_id.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.CHOOSE_OBJECT);
        } else {
            alert = AlertUnit.generateAlert(AlertInfo.CONFIRM_DELETE);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                employeeService.deleteEmployee(Integer.parseInt(input_id.getText()));
                alert = AlertUnit.generateAlert(AlertInfo.DELETE_SUCCESSFUL);

                setListEmployee();
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
        input_dob.setText("");
        input_address.setText("");
        input_phone.setText("");
        input_role.setText("");
        input_dob.setText("");
        input_password.setText("");
        linkImage = null;
        avatar.setImage(null);
    }

    public void searchEmployee() {
        if (searchTextField.getText().isEmpty()) {
            alert = AlertUnit.generateAlert(AlertInfo.ENTER_KEYWORD);
        } else {
            listDataEmployee = FXCollections.observableArrayList(employeeService.findEmployee(searchTextField.getText()));
            setListEmployee();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListDataEmployee();
        setListEmployee();
    }
}
