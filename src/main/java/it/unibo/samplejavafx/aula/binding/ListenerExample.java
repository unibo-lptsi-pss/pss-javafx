package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListenerExample extends Application {
    @Override
    public void start(Stage stage) {
        final Slider slider = new Slider(0, 100, 50);
        final TextArea log = new TextArea();
        log.setEditable(false);

        // a ChangeListener runs code on every change and receives the old and the new value:
        // use it for side effects, use a binding to keep two values in sync
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
            log.appendText(String.format("%.1f -> %.1f%n", oldValue.doubleValue(), newValue.doubleValue())));

        final VBox root = new VBox(10, slider, log);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - listener");
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(ListenerExample.class, args);
        }
    }
}
