package com.booksotre.controller.admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import com.booksotre.model.EmployeeModel;
import com.booksotre.model.OrderTamp;
import com.booksotre.service.impl.EmployeeService;
import com.booksotre.utils.AlertInfo;
import com.booksotre.utils.AlertUnit;

import lombok.Setter;

public class ProfileController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button putImageBtn;

    @FXML
    private TextField confirmNewPassword;

    @FXML
    private AnchorPane formUpdatePass;

    @FXML
    private AnchorPane formUpdateProfile;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField oldPassword;

    @FXML
    private Button passBtn;

    @FXML
    private TextField profileAddress;

    @FXML
    private ImageView profileAvatar;

    @FXML
    private Button profileBtn;

    @FXML
    private TextField profileEmail;

    @FXML
    private TextField profileGender;

    @FXML
    private TextField profileName;

    @FXML
    private TextField profilePhone;

    @FXML
    private TextField profileRole;

    @Setter
    private AnchorPane parentPane;

    private final EmployeeService employeeService = new EmployeeService();
    private Alert alert;
    private File selectedImage;
    private String linkImage;

    private boolean check = true;

    public void setProfile() {
        EmployeeModel employee = employeeService.getEmployeeByEmail(OrderTamp.emailEmployee);
        Image img;
        if (employee.getAvatar() != null) {
            img = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream(employee.getAvatar())),
                    200,
                    200,
                    false,
                    true);
        } else {
            img = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/defaultAvatar.jpg")),
                    200,
                    200,
                    false,
                    true);
        }
        profileAvatar.setImage(img);
        Circle clip2 = new Circle(
                profileAvatar.getFitWidth() / 2, profileAvatar.getFitHeight() / 2, profileAvatar.getFitWidth() / 2);
        profileAvatar.setClip(clip2);

        profileName.setText(employee.getEmployeeName());
        profileRole.setText(employee.getRole());
        profileAddress.setText(employee.getAddress());
        profileEmail.setText(employee.getEmail());
        profilePhone.setText(employee.getPhone());
        profileGender.setText(employee.getGender());
    }

    public void updateProfile() {
        if (check) {
            EmployeeModel employee = EmployeeModel.builder()
                    .employeeName(profileName.getText())
                    .phone(profilePhone.getText())
                    .email(profileEmail.getText())
                    .address(profileAddress.getText())
                    .gender(profileGender.getText())
                    .avatar(linkImage)
                    .build();

            if (!profileEmail.getText().equals(OrderTamp.emailEmployee)
                    && employeeService.checkAccountExist(profileEmail.getText())) {
                employeeService.updateProfile(employee);
                saveImageIntoProject();
                alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);
            } else {
                alert = AlertUnit.generateAlert(AlertInfo.EMAIL_EXISTED);
            }
        } else {
            if (oldPassword.getText().isEmpty()
                    || newPassword.getText().isEmpty()
                    || confirmNewPassword.getText().isEmpty()) {
                alert = AlertUnit.generateAlert(AlertInfo.LACK_OF_INFORMATION);
            } else {
                if (employeeService.passwordValid(OrderTamp.emailEmployee, oldPassword.getText())) {
                    if (newPassword.getText().length() < 8) {
                        alert = AlertUnit.generateAlert(AlertInfo.PASSWORD_INVALID);
                    } else if (newPassword.getText().equals(confirmNewPassword.getText())) {
                        alert = AlertUnit.generateAlert(AlertInfo.CONFIRMNEWPASS_INCORRECT);
                    } else {
                        employeeService.changePassword(OrderTamp.emailEmployee, newPassword.getText());
                        alert = AlertUnit.generateAlert(AlertInfo.UPDATE_SUCCESSFUL);
                    }
                } else {
                    alert = AlertUnit.generateAlert(AlertInfo.OLD_PASSWORD_INCORRECT);
                }
            }
        }
    }

    public void navigateProfile(ActionEvent e) {
        if (e.getSource() == profileBtn) {
            formUpdatePass.setVisible(false);
            formUpdateProfile.setVisible(true);

            check = true;
        } else if (e.getSource() == passBtn) {
            formUpdatePass.setVisible(true);
            formUpdateProfile.setVisible(false);

            check = false;
        } else if (e.getSource() == backBtn) {
            if (parentPane != null) {
                parentPane.setVisible(!parentPane.isVisible());
            }
            check = true;
        }
    }

    public void putImage() {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mở file ảnh", "*png", "*jpg"));

        selectedImage = file.showOpenDialog(null);

        if (selectedImage != null) {
            Image img = new Image(selectedImage.toURI().toString(), 200, 200, false, true);
            profileAvatar.setImage(img);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProfile();
    }
}
