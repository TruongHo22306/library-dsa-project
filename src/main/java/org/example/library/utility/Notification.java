package org.example.library.utility;

import javafx.scene.control.Alert;

public class Notification {
    public static void alter(String message, Alert.AlertType type, String title, String header) { // warning, infomation, error
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static boolean confirm(String message, String title, String header) { // co 2 cai nu yes no
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult().getButtonData().isDefaultButton();
    }
}
