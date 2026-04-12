package org.example.sistemalogin.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.sistemalogin.model.Admin;
import org.example.sistemalogin.model.Worker;
import org.example.sistemalogin.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField password;

    @FXML
    private Button send;

    @FXML
    private TextField user;

    private ObservableList<Admin> admins;
    private ObservableList<Worker> workers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        initGUI();
        actions();
    }

    private void instances() {
        admins = FXCollections.observableArrayList(new Admin("admin", "admin", "123456A", "admin@admin.com", "admin"));
        workers = FXCollections.observableArrayList(); // Ambas inicializaciones temporales hasta importar usuarios de los archivos .obj
    }

    private void initGUI() {
        FileController fileController = new FileController();
        fileController.importWorkers(workers);
    }

    private void actions() {
         send.setOnAction(event -> {
            String username = user.getText();
            String pass = password.getText();
            Alert alert;
            Worker worker; // Para guardar el trabajador encontrado en caso de que haga login uno (para el fichaje posterior)
            if (username.isEmpty() || pass.isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Debes introducir un usuario y una contraseña. Ambos campos deben tener un valor.");
                alert.show();
            } else if (checkAdmin(username, pass)) {
                generateAdminStage();
            } else if ((worker = checkWorker(username, pass)) != null) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fichaje");
                alert.setContentText(String.format("Usuario: %s %s - %s. ¿Quieres fichar?", worker.getName(), worker.getSurname(), worker.getMail()));
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    worker.register();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fichaje realizado");
                    alert.setContentText("¡Has fichado con éxito!");
                }
                user.setText("");
                password.setText("");
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("El usuario o la contraseña no son correctos. Vuelve a introducir los datos.");
                alert.show();
                user.setText("");
                password.setText("");
            }
        });
    }

    private boolean checkAdmin(String username, String pass) {
        // Comprobar si el usuario y la contraseña están en la lista de admins
        for (Admin admin : admins) {
            if (username.equalsIgnoreCase(admin.getMail()) && pass.equalsIgnoreCase(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private Worker checkWorker(String username, String pass) {
        // Comprobar si el usuario y la contraseña están en la lista de trabajadores
        for (Worker worker : workers) {
            if (username.equalsIgnoreCase(worker.getMail()) && pass.equalsIgnoreCase(worker.getPassword())) {
                return worker;
            }
        }
        return null;
    }

    private void generateAdminStage() {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Administración de usuarios");
            stage.setScene(scene);
            stage.show();
            ((Stage)send.getScene().getWindow()).close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No se ha encontrado la pantalla que intenta cargar.");
            alert.show();
        }
    }

}
