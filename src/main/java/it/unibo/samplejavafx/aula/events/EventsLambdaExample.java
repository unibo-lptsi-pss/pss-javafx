package it.unibo.samplejavafx.aula.events;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EventsLambdaExample extends Application {
    @Override
    public void start(Stage stage) {
        final Label lbl = new Label("Label text here...");
        final Button btn = new Button("Click me");

        // Same wiring as EventsExample, with lambdas instead of handler classes
        btn.setOnMouseClicked(event -> lbl.setText("Hello, JavaFX World!"));
        btn.addEventHandler(ActionEvent.ACTION, event -> lbl.setText("Hello, JavaFX World!"));

        stage.setTitle("JavaFX - Events with lambdas");
        stage.setScene(new Scene(new HBox(btn, lbl), 300, 250));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(EventsLambdaExample.class, args);
        }
    }
}
