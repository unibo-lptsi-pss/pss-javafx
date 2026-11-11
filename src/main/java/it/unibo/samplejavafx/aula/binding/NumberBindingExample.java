package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class NumberBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final Slider slider = new Slider(0, 100, 50);
        final Slider copy = new Slider(0, 100, 50);
        final Label value = new Label();
        final Circle circle = new Circle(50, Color.STEELBLUE);

        // number -> string
        value.textProperty().bind(slider.valueProperty().asString("Value: %.0f"));
        // number -> number
        circle.radiusProperty().bind(slider.valueProperty());
        // the two sliders always move together
        copy.valueProperty().bindBidirectional(slider.valueProperty());

        final VBox root = new VBox(10, slider, copy, value, circle);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - numbers");
        stage.setScene(new Scene(root, 300, 340));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(NumberBindingExample.class, args);
        }
    }
}
