module org.example.sistemalogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires jdk.security.jgss;

    exports org.example.sistemalogin;
    opens org.example.sistemalogin to javafx.fxml;

    exports org.example.sistemalogin.controller;
    opens org.example.sistemalogin.controller to javafx.fxml;

    exports org.example.sistemalogin.model;
    opens org.example.sistemalogin.model to javafx.fxml;


}