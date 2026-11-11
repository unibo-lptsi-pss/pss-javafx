package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnidirectionalBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final TextField input = new TextField();
        final Label mirror = new Label();
        final Button change = new Button("Change label");
        final Button unbind = new Button("Unbind");

        // mirror follows input, but not the other way round
        mirror.textProperty().bind(input.textProperty());

        // while bound, this throws "A bound value cannot be set"
        change.setOnAction(event -> mirror.setText("changed by hand"));
        // after unbind, the label keeps its last value and can be set again
        unbind.setOnAction(event -> mirror.textProperty().unbind());

        final VBox root = new VBox(10, input, mirror, new HBox(10, change, unbind));
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - unidirectional");
        stage.setScene(new Scene(root, 300, 150));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(UnidirectionalBindingExample.class, args);
        }
    }
}
