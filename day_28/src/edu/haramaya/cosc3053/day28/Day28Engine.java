package edu.haramaya.cosc3053.day28;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class FormApp extends Application {

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Row 0: heading spanning both columns
        Label heading = new Label("Student Registration");
        grid.add(heading, 0, 0, 2, 1);
        GridPane.setHalignment(heading, HPos.CENTER);

        // Row 1: name
        grid.add(new Label("Full name:"), 0, 1);
        grid.add(new TextField(), 1, 1);

        // Row 2: email
        grid.add(new Label("Email:"), 0, 2);
        grid.add(new TextField(), 1, 2);

        // Row 3: password
        grid.add(new Label("Password:"), 0, 3);
        grid.add(new PasswordField(), 1, 3);

        // Row 4: button spanning both columns
        Button register = new Button("Register");
        register.setMaxWidth(Double.MAX_VALUE);
        grid.add(register, 0, 4, 2, 1);

        // Column 1 grows when the window grows
        ColumnConstraints labelColumn = new ColumnConstraints();
        ColumnConstraints fieldColumn = new ColumnConstraints();
        fieldColumn.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(labelColumn, fieldColumn);

        stage.setTitle("Day 28: GridPane Form");
        stage.setScene(new Scene(grid, 380, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}