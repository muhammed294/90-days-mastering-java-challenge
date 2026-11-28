package edu.haramaya.cosc3053.day25;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFxApp extends Application {

    @Override
    public void init() {
        System.out.println("init(): runs first, before the window exists");
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, Haramaya University!");
        Scene scene = new Scene(new StackPane(label), 400, 200);
        stage.setTitle("Day 25: JavaFX");
        stage.setScene(scene);
        stage.show();
        System.out.println("start(): the window is now showing");
    }

    @Override
    public void stop() {
        System.out.println("stop(): the window was closed");
    }

    public static void main(String[] args) {
        launch(args);
    }
}