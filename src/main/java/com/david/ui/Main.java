package com.david.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("NetFlix Stream Search");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/streamSearch.fxml"));
            VBox vbox = loader.load();
            Scene scene = new Scene(vbox);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading streamSearch.fxml");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
