package it.unibo.samplejavafx.aula.events;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EventsExample extends Application {
    @Override
    public void start(Stage stage) {
        final Label lbl = new Label("Label text here...");
        final Button btn = new Button("Click me");

        // Wiring the handlers to the button and label
        btn.setOnMouseClicked(new ClickHandler(lbl));
        btn.addEventHandler(ActionEvent.ACTION, new ActionHandler(lbl));

        stage.setTitle("JavaFX - Events");
        stage.setScene(new Scene(new HBox(btn, lbl), 300, 250));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(EventsExample.class, args);
        }
    }
}
