package edu.haramaya.cosc3053.day27;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutApp extends Application {

    @Override
    public void start(Stage stage) {
        // TOP: an HBox with the title
        Label title = new Label("Haramaya Student Portal");
        HBox top = new HBox(title);
        top.setPadding(new Insets(10));

        // LEFT: a VBox menu, with buttons that stretch to the same width
        Button home = new Button("Home");
        Button courses = new Button("Courses");
        Button grades = new Button("Grades");
        for (Button button : new Button[]{home, courses, grades}) {
            button.setMaxWidth(Double.MAX_VALUE);
        }
        VBox menu = new VBox(10, home, courses, grades);
        menu.setPadding(new Insets(10));

        // CENTER: takes all the remaining space
        TextArea content = new TextArea("Resize the window: this area grows and shrinks.");

        // BOTTOM: an HBox with a spacer that pushes "Exit" to the right
        Label status = new Label("Ready");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button exit = new Button("Exit");
        HBox bottom = new HBox(10, status, spacer, exit);
        bottom.setPadding(new Insets(10));

        // The BorderPane is the root
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setLeft(menu);
        root.setCenter(content);
        root.setBottom(bottom);

        stage.setTitle("Day 27: Layout Panes");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}