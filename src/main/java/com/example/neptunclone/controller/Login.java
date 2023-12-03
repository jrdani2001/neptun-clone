package com.example.neptunclone.controller;

import com.example.neptunclone.connect.dbHandler;
import com.example.neptunclone.model.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
    @FXML
    public TextField passwordfield;
    @FXML
    public Button btn_login;
    @FXML
    public TextField textfield_username;

    private Connection connection;

    @FXML
    private AnchorPane loginPane;  // Az FXML fájlban beállított fx:id
    //String loggedInUserId;

    /*private Main mainController;

    public Login(Main mainController) {
        this.mainController = mainController;
    }
    public Login() {

    }*/

    public boolean login() throws SQLException {
        connection = dbHandler.getConnection();
        String connect = "SELECT id FROM FELHASZNALOK where felhasznalonev = '"
                + textfield_username.getText() + "' and jelszo = '" + passwordfield.getText() + "';";
        try {
            ResultSet set = connection.createStatement().executeQuery(connect);
            while (set.next()) {
                if (set.getInt(1) > 0) {
                    //loggedInUserId = set.getString(1);

                    //mainController.setLoggedInUserId(set.getInt(1));
                   /* btn_login.getScene().getWindow().hide();
                    ViewModel.getInstance().getViewFactory().showMainWindow();*/

                    //System.out.println(loggedInUserId);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }
    public void btnLoginAction(ActionEvent actionEvent) {
        try {
            if (login()) {
                // Ha a belépés sikeres, hozz létre egy példányt a Main osztályból és adj át neki a felhasználónevet
                Main mainController = new Main();
                btn_login.getScene().getWindow().hide();
                ViewModel.getInstance().getViewFactory().showMainWindow(loginPane, mainController);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Connected() {
        if (!dbHandler.isConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nincs kapcsolat az adatbázissal");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       Connected();
    }
}
