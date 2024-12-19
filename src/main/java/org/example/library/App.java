package org.example.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {


        stage = primaryStage;
        scene = new Scene(loadFXML("LoginView"), 493, 327);
        stage.setResizable(false);
        stage.setTitle("LOG IN");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void setRoot(String fxml, String title) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);

        stage.setWidth(root.prefWidth(-1));
        stage.setHeight(root.prefHeight(-1));
        stage.setTitle(title);
    }

    public static void setRootPop(String fxml, String title, boolean resizable, double width, double height) throws IOException {
        Stage stage = new Stage();
        Scene newScene = new Scene(loadFXML(fxml), width, height);
        stage.setResizable(resizable);
        stage.setScene(newScene);
        stage.setTitle(title);


        stage.showAndWait();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}