package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ConditionalBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final Slider temperature = new Slider(-10, 40, 20);
        final Label value = new Label();
        final Label description = new Label();
        final Circle indicator = new Circle(40);

        final BooleanBinding hot = temperature.valueProperty().greaterThan(25);
        final BooleanBinding cold = temperature.valueProperty().lessThan(10);

        value.textProperty().bind(temperature.valueProperty().asString("%.0f °C"));
        // when(condition).then(a).otherwise(b) is the binding version of condition ? a : b
        // here: hot ? "Hot" : (cold ? "Cold" : "Mild")
        description.textProperty().bind(
            Bindings.when(hot)
                .then("Hot")
                .otherwise(Bindings.when(cold)
                    .then("Cold")
                    .otherwise("Mild"))
        );
        indicator.fillProperty().bind(
            Bindings.when(hot)
                .then(Color.RED)
                .otherwise(Bindings.when(cold)
                    .then(Color.DODGERBLUE)
                    .otherwise(Color.GREEN))
        );

        final VBox root = new VBox(10, temperature, value, description, indicator);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - conditions");
        stage.setScene(new Scene(root, 300, 220));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(ConditionalBindingExample.class, args);
        }
    }
}
