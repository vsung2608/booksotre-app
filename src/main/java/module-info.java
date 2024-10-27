module com.booksotre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires java.sql;
    requires mysql.connector.j;
    requires jbcrypt;

    opens com.booksotre.controller.admin to
            javafx.fxml;

    exports com.booksotre.controller.admin;

    opens com.booksotre.main to
            javafx.graphics;

    exports com.booksotre.main;

    opens com.booksotre.model to
            javafx.base;

}
