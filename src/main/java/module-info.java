module com.booksotre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires static lombok;
    requires mysql.connector.j;
    requires jbcrypt;
    requires org.slf4j;
    requires jasperreports;
    requires java.sql;
    requires fontawesomefx;
    requires java.smartcardio;

    opens com.booksotre.controller.admin to
            javafx.fxml;

    exports com.booksotre.controller.admin;

    opens com.booksotre.controller.user to
            javafx.fxml;

    exports com.booksotre.controller.user;

    opens com.booksotre.main to
            javafx.graphics;

    exports com.booksotre.main;

    opens com.booksotre.model to
            javafx.base;
}
