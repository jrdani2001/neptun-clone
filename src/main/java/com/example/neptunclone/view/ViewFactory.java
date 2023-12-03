package com.example.neptunclone.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ViewFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewFactory.class);
    private AnchorPane Main;
    public ViewFactory(){

    }

    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));

        createStage(fxmlLoader);
    }

    public void showMainWindow(AnchorPane loginPane, com.example.neptunclone.controller.Main mainController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));

        createStage(fxmlLoader);
    }

    private void createStage (FXMLLoader fxmlLoader) {
        Scene scene = null;
        try {
            scene= new Scene(fxmlLoader.load());

        } catch (IOException e) {
            LOGGER.error(String.valueOf(new RuntimeException(e)));
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("beadando");
        stage.show();
    }
}
