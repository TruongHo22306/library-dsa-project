module org.example.library {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.library to javafx.fxml;
    opens org.example.library.controllers to javafx.fxml;
    opens org.example.library.models to javafx.fxml;
    opens org.example.library.daos to javafx.fxml;
    opens org.example.library.bus to javafx.fxml;


    exports org.example.library;
    exports org.example.library.controllers;
    exports org.example.library.models;
    exports org.example.library.daos;
    exports org.example.library.bus;
}