module com.example.neptunclone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.slf4j;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.h2database;

    opens com.example.neptunclone to javafx.fxml;
    exports com.example.neptunclone.controller;
    exports com.example.neptunclone;
    exports com.example.neptunclone.model;
    exports com.example.neptunclone.connect;
    exports com.example.neptunclone.view;
    opens com.example.neptunclone.controller to javafx.fxml;
}