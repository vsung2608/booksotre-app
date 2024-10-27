package com.booksotre.main;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/admin/LoginFXML.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("Hệ thông cửa hàng bán sách");
        stage.setMinHeight(400);
        stage.setWidth(600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
