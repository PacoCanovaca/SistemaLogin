package org.example.sistemalogin.model;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.sistemalogin.HelloApplication;
import org.example.sistemalogin.controller.UserdataController;

import java.io.IOException;

public class Admin extends User {

    public Admin(String name, String surname, String dni, String mail, String password) {
        super(name, surname, dni, mail, password);
    }

    public static void addWorker(ObservableList<Worker> workers, String name, String surname, String dni, String mail, String password, Alert alert) {
        workers.add(new Worker(name, surname, dni, mail, password));
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario añadido");
        alert.setContentText("Se ha dado de alta al usuario correctamente.");
        alert.show();
    }

    public static void deleteWorker(ObservableList<Worker> workers, Worker worker, Alert alert) {
        workers.remove(worker);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario eliminado");
        alert.setContentText("Se ha dado de baja al usuario correctamente.");
        alert.show();
    }

    public static void seeWorker(Stage stage, Worker worker) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userdata-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UserdataController userdataController = fxmlLoader.getController();
            userdataController.setWorker(worker);
            stage.setTitle("Datos del usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No se ha encontrado la pantalla que intenta cargar.");
            alert.show();
        }
    }

    public static void changePass(Worker worker, String newPassword, Alert alert) {
        worker.setPassword(newPassword);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contraseña modificada");
        alert.setContentText("Se ha modificado la contraseña del usuario indicado.");
        alert.show();
    }

}
