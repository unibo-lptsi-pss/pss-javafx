package it.unibo.samplejavafx.aula.buttonlabel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Example1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final Label lbl = new Label();
        lbl.setText("Label text here...");

        final Button btn = new Button();
        btn.setText("Click me");

        final HBox root = new HBox();
        root.getChildren().add(btn);
        root.getChildren().add(lbl);

        stage.setTitle("JavaFX - Example 1");
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(Example1.class, args);
        }
    }
}
