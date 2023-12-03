package com.example.neptunclone.controller;

import com.example.neptunclone.connect.dbHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {

    @FXML
    public TableView<Course> targytable;
    private String loggedInUser;
    private static Connection connection;

   /* private int loggedInUserId;

    public void setLoggedInUserId(int loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }


    public Main() {
    }

    public Main(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
*/

    public void initialize() {
        // Inicializálj egy példa felhasználót
        int userId = 1;
        System.out.println(userId);

        // Tárgyak listája
        ObservableList<Course> courses = FXCollections.observableArrayList(
                new Course(1, "Matematika"),
                new Course(2, "Informatika"),
                new Course(3, "Fizika")
        );

        // Tárgyak oszlop beállítása
        TableColumn<Course, String> courseColumn = new TableColumn<>("Tárgyak");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        // Gombok oszlop beállítása
        TableColumn<Course, Button> buttonColumn = new TableColumn<>("Gombok");
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("assignButton"));

        // Oszlopok hozzáadása a TableView-hoz
        targytable.getColumns().addAll(courseColumn, buttonColumn);

        // Tárgyak hozzáadása a TableView-hoz
        targytable.setItems(courses);

        // Gombok kattintás eseménykezelője
        for (Course course : courses) {
            course.getAssignButton().setOnAction(event -> {
                try {
                    course.saveToDatabase(loggedInUser);
                    // Frissítsd a TableView-t, ha szükséges
                    targytable.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static class Course {
        private final int courseId;
        private final String courseName;
        private final Button assignButton;

        public Course(int courseId, String courseName) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.assignButton = new Button("Felvesz");
        }

        public String getCourseName() {
            return courseName;
        }

        public Button getAssignButton() {
            return assignButton;
        }

        public void saveToDatabase(String userId) throws SQLException {
            connection = dbHandler.getConnection();
            // Itt hajtsd végre a tárgy felvételét az adatbázisba userId alapján
            System.out.println("Felhasználó: " + userId + ", Tárgy: " + courseName);
            // Implementáld az adatbázisba mentést a példa szerint
            String insertFields = "insert into USER_COURSES (user_id, course_name) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertFields);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, courseName);
            preparedStatement.executeUpdate();
        }
    }
}
