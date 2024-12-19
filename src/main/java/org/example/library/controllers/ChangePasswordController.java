package org.example.library.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import org.example.library.bus.AccountBus;
import org.example.library.utility.Notification;
import org.example.library.utility.SecurityContextHolder;

public class ChangePasswordController {
    public PasswordField pwCur;
    public PasswordField pwNew;
    public PasswordField pwReEnter;

    private final AccountBus accountBus;

    public ChangePasswordController() {
        accountBus = new AccountBus();
    }

    public void onClickOk(ActionEvent actionEvent) {
        String cur = pwCur.getText();
        String newPw = pwNew.getText();
        String reEnter = pwReEnter.getText();

        if (cur.isEmpty() || newPw.isEmpty() || reEnter.isEmpty()) {
            Notification.alter("Please enter all the required information.", Alert.AlertType.WARNING, "Warning", null);
            return;
        }

        if (!newPw.equals(reEnter)) {
            Notification.alter("New password does not match", Alert.AlertType.WARNING, "Warning", null);
            return;
        }

        if (!SecurityContextHolder.getInstance().getPassword().equals(cur)) {
            Notification.alter("Current password is incorrect", Alert.AlertType.WARNING, "Warning", null);
            return;
        }

        accountBus.changePassword(SecurityContextHolder.getInstance().getUsername(), newPw);
        Notification.alter("Password changed successfully", Alert.AlertType.INFORMATION, "Notification", null);
    }
}
