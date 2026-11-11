package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CounterExample extends Application {
    @Override
    public void start(Stage stage) {
        final Counter counter = new Counter();
        final Label value = new Label();
        final Button plus = new Button("+1");
        final Button minus = new Button("-1");
        final Button reset = new Button("Reset");

        // the view follows the model: no manual refresh after a change
        value.textProperty().bind(counter.countProperty().asString("Count: %d"));
        minus.disableProperty().bind(counter.countProperty().lessThanOrEqualTo(0));
        reset.disableProperty().bind(counter.countProperty().isEqualTo(0));

        // the buttons only change the model
        plus.setOnAction(event -> counter.increment());
        minus.setOnAction(event -> counter.decrement());
        reset.setOnAction(event -> counter.setCount(0));

        final VBox root = new VBox(10, value, new HBox(10, minus, plus, reset));
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - model property");
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(CounterExample.class, args);
        }
    }
}
