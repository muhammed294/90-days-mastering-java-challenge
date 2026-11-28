package edu.haramaya.cosc3053.day26;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneGraphApp extends Application {

    @Override
    public void start(Stage stage) {
        // leaf nodes
        Label title = new Label("Scene Graph Demo");
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");

        // parent nodes
        HBox buttons = new HBox(10, ok, cancel);
        VBox root = new VBox(15, title, buttons);

        // scene and stage
        Scene scene = new Scene(root, 300, 150);
        stage.setTitle("Day 26: Scene Graph");
        stage.setScene(scene);
        stage.show();

        printTree(root, 0);
    }

    private static void printTree(Node node, int depth) {
        System.out.println("  ".repeat(depth) + node.getClass().getSimpleName());
        if (node instanceof Pane pane) {
            for (Node child : pane.getChildren()) {
                printTree(child, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}