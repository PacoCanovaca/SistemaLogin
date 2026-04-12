package org.example.sistemalogin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.example.sistemalogin.model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class UserdataController implements Initializable {

    @FXML
    private Text userDNI;

    @FXML
    private Text userMail;

    @FXML
    private Text userName;

    @FXML
    private Text userPassword;

    @FXML
    private Text userSurname;

    private Worker worker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setWorker(Worker worker) {
        this.worker = worker;
        initGUI(); // ejecuto aquí initGUI en lugar de en initialize porque, en ese caso, el worker todavía no estaría inicializado en la clase AdminController (primero carga la ventana con el .load y luego asigna el valor de la variable worker con .loadController y .setWorker)
    }

    private void initGUI() {
        userName.setText(worker.getName());
        userSurname.setText(worker.getSurname());
        userDNI.setText(worker.getDni());
        userMail.setText(worker.getMail());
        userPassword.setText(worker.getPassword());
    }
}
