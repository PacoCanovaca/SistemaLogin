package org.example.sistemalogin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.sistemalogin.controller.FileController;
import org.example.sistemalogin.model.Worker;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Login de Usuarios");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FileController fileController = new FileController();
        ObservableList<Worker> workers = FXCollections.observableArrayList(new Worker("Paco", "Canovaca", "123123A", "paco@gmail.com", "patata"), new Worker("Paula", "Castillo", "234234B", "paula@gmail.com", "lechuga"));
        fileController.exportWorkers(workers);
        launch();
    }
}