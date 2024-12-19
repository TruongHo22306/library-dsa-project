package org.example.library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.library.App;
import org.example.library.utility.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public ListView<String> optionMenu;
    public AnchorPane pane;
    public Menu asc;
    public Menu desc;

    private BookController bookController;
    private ReaderController readerController;
    private BorrowController borrowController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionMenu.getItems().addAll("Book Management", "Reader Management", "Borrow Management", "Change Password");
        optionMenu.getSelectionModel().selectFirst();

        loadView("BookView");
        updateSortingMenu("Book Management");
    }

    private void loadView(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/library/" + fxmlName + ".fxml"));
            AnchorPane childPane = loader.load();
            pane.getChildren().setAll(childPane);

            if ("BookView".equals(fxmlName)) {
                bookController = loader.getController();
            } else if ("ReaderView".equals(fxmlName)) {
                readerController = loader.getController();
            } else if ("BorrowView".equals(fxmlName)) {
                borrowController = loader.getController();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChooseMenu(MouseEvent mouseEvent) {
        String selectedMenu = optionMenu.getSelectionModel().getSelectedItem();
        switch (selectedMenu) {
            case "Book Management":
                loadView("BookView");
                updateSortingMenu("Book Management");
                break;
            case "Reader Management":
                loadView("ReaderView");
                updateSortingMenu("Reader Management");
                break;
            case "Borrow Management":
                loadView("BorrowView");
                updateSortingMenu("Borrow Management");
                break;
            case "Change Password":
                loadView("ChangePasswordView");
                updateSortingMenu(null);
                break;
        }
    }

    private void updateSortingMenu(String menuType) {
        asc.getItems().clear();
        desc.getItems().clear();

        if (menuType == null) return;

        switch (menuType) {
            case "Book Management":
                addSortingMenuItems("By Title", "By Quantity");
                break;
            case "Reader Management":
                addSortingMenuItems("By Name");
                break;
            case "Borrow Management":
                addSortingMenuItems("By Borrow Date", "By Due Date");
                break;
        }
    }

    private void addSortingMenuItems(String... criteria) {
        for (String criterion : criteria) {
            MenuItem ascItem = new MenuItem(criterion);
            MenuItem descItem = new MenuItem(criterion);

            ascItem.setOnAction(event -> onSortingSelected(criterion, true));
            descItem.setOnAction(event -> onSortingSelected(criterion, false));

            asc.getItems().add(ascItem);
            desc.getItems().add(descItem);
        }
    }

    private void onSortingSelected(String criterion, boolean ascending) {
        System.out.println("Sorting by " + criterion + " in " + (ascending ? "ascending" : "descending") + " order.");

        String currentMenu = optionMenu.getSelectionModel().getSelectedItem();
        switch (currentMenu) {
            case "Book Management":
                if (bookController != null) {
                    bookController.sortData(criterion, ascending);
                }
                break;
            case "Reader Management":
                if (readerController != null) {
                    readerController.sortData(criterion, ascending);
                    break;

                }

            case "Borrow Management":
                if (borrowController != null) {
                    borrowController.sortData(criterion, ascending);
                }
                break;
        }



    }

    public void onClickLogout (ActionEvent actionEvent) throws IOException {
        if (!Notification.confirm("Are you sure you want to log out?", "Logout", null)) {
            return;
        }

        App.setRoot("LoginView", "Login");
    }
}
