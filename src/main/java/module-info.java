module com.booksotre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires java.sql;
    requires mysql.connector.j;

    opens com.booksotre.controller to javafx.fxml;
    exports com.booksotre.controller;

    opens com.booksotre.main to javafx.graphics;
    exports com.booksotre.main;
}