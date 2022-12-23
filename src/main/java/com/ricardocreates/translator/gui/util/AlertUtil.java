package com.ricardocreates.translator.gui.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    private AlertUtil() {
    }

    public static Alert showErrorAlert(String title, String message) {
        return showAlert(Alert.AlertType.ERROR, title, message);
    }

    public static Alert showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
        return alert;
    }
}
