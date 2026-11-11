package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final TextField input = new TextField();
        final Label mirror = new Label();
        // connette la label con il valore del textfield
        mirror.textProperty().bindBidirectional(input.textProperty());
        mirror.setText("default");

        stage.setTitle("JavaFX - Binding");
        stage.setScene(new Scene(new VBox(input, mirror), 300, 100));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(BindingExample.class, args);
        }
    }
}
