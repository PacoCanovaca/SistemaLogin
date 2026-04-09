package org.example.sistemalogin.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.sistemalogin.HelloApplication;
import org.example.sistemalogin.model.Worker;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button addWorkerButton;

    @FXML
    private TextField addWorkerID;

    @FXML
    private TextField addWorkerMail;

    @FXML
    private TextField addWorkerName;

    @FXML
    private TextField addWorkerPass;

    @FXML
    private TextField addWorkerSurname;

    @FXML
    private Button backButton;

    @FXML
    private Button changePassButton;

    @FXML
    private TextField changePassID;

    @FXML
    private TextField changePassNewPassword;

    @FXML
    private TextField deleteWorker;

    @FXML
    private Button deleteWorkerButton;

    @FXML
    private ListView<String> registers;
    private ObservableList<String> registersList;

    @FXML
    private Button saveUsersButton;

    @FXML
    private TextField seeWorker;

    @FXML
    private Button seeWorkerButton;

    @FXML
    private ListView<Worker> workersList;
    private ObservableList<Worker> workers;

    private FileController fileController = new FileController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        initGUI();
        actions();
    }

    public void instances() {
        workers = FXCollections.observableArrayList();
        registersList = FXCollections.observableArrayList();
        fileController.importWorkers(workers);
        fileController.importRegisters(registersList);
    }

    public void initGUI() {
        workersList.setItems(workers);
        registers.setItems(registersList);
    }

    public void actions() {
        backButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Volver a inicio de sesión");
            alert.setContentText("¿Estás seguro de que quieres cerrar sesión y volver al inicio de sesión? ¡Recuerda que debes guardar los cambios realizados antes de salir del programa!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                generateLoginStage();
            }
        });
        saveUsersButton.setOnAction(event -> {
            fileController.exportWorkers(workers);
        });
    }

    public void generateLoginStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Login de Usuarios");
            stage.setScene(scene);
            stage.show();
            ((Stage)backButton.getScene().getWindow()).close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No se ha encontrado la pantalla que intenta cargar.");
            alert.show();
        }
    }
}
