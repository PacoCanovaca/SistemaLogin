package org.example.sistemalogin.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.sistemalogin.model.Admin;
import org.example.sistemalogin.model.User;
import org.example.sistemalogin.model.Worker;
import org.example.sistemalogin.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // Esta clase sirve para controlar el Login inicial, es decir, comparar el usuario y contraseña con los introducidos en los distintos tipos de trabajadores.

    // En definitiva, recibe las listas de usuarios (administradores y trabajadores) y valida el login

    // Seguramente tenga que hacer polimorfismo y así tener una sola lista de objetos User en lugar de dos listas (Una para objetos Admin y otra para objetos Worker)

    @FXML
    private TextField password;

    @FXML
    private Button send;

    @FXML
    private TextField user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        actions();
    }

    private ObservableList<Admin> admins;
    private ObservableList<Worker> workers;

    public void instances() {
        admins = FXCollections.observableArrayList(new Admin("admin", "admin", "123456A", "admin@admin.com", "admin"));
        workers = FXCollections.observableArrayList(); // Ambas inicializaciones temporales hasta importar usuarios de los archivos .obj
    }

    public void actions() {
         send.setOnAction(event -> {
            String username = user.getText();
            String pass = password.getText();
            if (checkAdmin(username, pass)) {
                System.out.println("Eres un admin");
            } else {
                System.out.println("No estás registrado");
            }
        });
    }

    public boolean checkAdmin(String username, String pass) {
        // Comprobar si el usuario y la contraseña están en la lista de admins
        for (Admin admin : admins) {
            if (username.equalsIgnoreCase(admin.getMail()) && pass.equalsIgnoreCase(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

//    public boolean checkUser(String username, String pass) {
//        // Comprobar si el usuario y la contraseña están en la lista de trabajadores
//    }

}
