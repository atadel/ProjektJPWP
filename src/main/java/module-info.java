module com.example.parapet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parapet to javafx.fxml;
    exports com.example.parapet;
}