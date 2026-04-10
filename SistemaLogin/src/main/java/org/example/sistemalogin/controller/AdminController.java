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
            alert.setContentText("¿Quieres cerrar sesión? ¡Recuerda guardar los cambios antes!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                generateLoginStage();
            }
        });

        saveUsersButton.setOnAction(event -> {
            fileController.exportWorkers(workers);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usuarios guardados");
            alert.setContentText("Se ha exportado correctamente la lista de usuarios.");
            alert.show();
        });

        addWorkerButton.setOnAction(event -> {
            Alert alert;
            String name = addWorkerName.getText();
            String surname = addWorkerSurname.getText();
            String mail = addWorkerMail.getText();
            String dni = addWorkerID.getText();
            String password = addWorkerPass.getText();
            if (name.isEmpty() || surname.isEmpty() || mail.isEmpty() || dni.isEmpty() || password.isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Campos incompletos");
                alert.setContentText("Debes rellenar todos los campos para crear un nuevo usuario.");
                alert.show();
            } else if (checkMail(mail)) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Correo existente");
                alert.setContentText("El correo que has introducido ya está registrado. Prueba con otro.");
                alert.show();
            } else if (checkId(dni)) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DNI ya registrado");
                alert.setContentText("Ya existe un usuario registrado con ese DNI.");
                alert.show();
            } else {
                workers.add(new Worker(name, surname, dni, mail, password));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario añadido");
                alert.setContentText("Se ha dado de alta al usuario correctamente.");
                alert.show();
                addWorkerName.setText("");
                addWorkerSurname.setText("");
                addWorkerMail.setText("");
                addWorkerID.setText("");
                addWorkerPass.setText("");
            }
        });

        deleteWorkerButton.setOnAction(event -> {
            Alert alert;
            for (Worker worker : workers) {
                if (deleteWorker.getText().equalsIgnoreCase(worker.getDni())) {
                    workers.remove(worker);
                    deleteWorker.setText("");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Usuario eliminado");
                    alert.setContentText("Se ha dado de baja al usuario correctamente.");
                    alert.show();
                    return;
                }
            }
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario no encontrado");
            alert.setContentText("No se ha encontrado ningún usuario con este DNI");
            alert.show();
            deleteWorker.setText("");
        });

        seeWorkerButton.setOnAction(event -> {
            for (Worker worker : workers) {
                if (seeWorker.getText().equalsIgnoreCase(worker.getDni())) {
                    // todo Generar ventana externa para la información y los registros del usuario. Mantener esta abierta para seguir trabajando con ella
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario no encontrado");
            alert.setContentText("No se ha encontrado ningún usuario con este DNI");
            alert.show();
            seeWorker.setText("");
        });

        changePassButton.setOnAction(event -> {
            Alert alert;
            for (Worker worker : workers) {
                if (changePassID.getText().equalsIgnoreCase(worker.getDni())) {
                    worker.setPassword(changePassNewPassword.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Contraseña modificada");
                    alert.setContentText("Se ha modificado la contraseña del usuario indicado.");
                    alert.show();
                    return;
                }
            }
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Usuario no encontrado");
            alert.setContentText("No se ha encontrado ningún usuario con este DNI");
            alert.show();
            changePassID.setText("");
            changePassNewPassword.setText("");
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

    public boolean checkId(String dni) {
        for (Worker worker : workers) {
            if (worker.getDni().equalsIgnoreCase(dni)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMail(String mail) {
        for (Worker worker : workers) {
            if (worker.getMail().equalsIgnoreCase(mail)) {
                return true;
            }
        }
        return false;
    }
}
