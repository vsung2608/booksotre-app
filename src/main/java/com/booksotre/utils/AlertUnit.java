package com.booksotre.utils;

import javafx.scene.control.Alert;

public class AlertUnit {
    public static Alert generateAlert(AlertInfo alertInfo) {
        Alert alert = new Alert(alertInfo.getType());
        alert.setTitle(alertInfo.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(alertInfo.getMessage());
        alert.showAndWait();
        return alert;
    }
}