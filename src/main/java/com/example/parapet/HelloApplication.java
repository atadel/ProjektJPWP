package com.example.parapet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parapet.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 612*4/3, 408);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Wirtualny parapet - fxml");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}