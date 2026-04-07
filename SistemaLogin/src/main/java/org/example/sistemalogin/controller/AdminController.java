package org.example.sistemalogin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
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
    private Button back;

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
    private Button seeRegisters;

    @FXML
    private TextField seeWorker;

    @FXML
    private Button seeWorkerButton;

    @FXML
    private ListView<?> workersList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
