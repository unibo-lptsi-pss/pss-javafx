package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final Slider weight = new Slider(40, 150, 70);
        final Slider height = new Slider(140, 210, 175);
        final Label weightLabel = new Label();
        final Label heightLabel = new Label();
        final Label bmiLabel = new Label();
        final Label categoryLabel = new Label();

        weightLabel.textProperty().bind(weight.valueProperty().asString("Weight: %.0f kg"));
        heightLabel.textProperty().bind(height.valueProperty().asString("Height: %.0f cm"));

        // any Java code can compute a binding: list its dependencies after the lambda
        // (forget one, and the binding is not recomputed when it changes)
        final DoubleBinding bmi = Bindings.createDoubleBinding(
            () -> weight.getValue() / Math.pow(height.getValue() / 100, 2),
            weight.valueProperty(), height.valueProperty());
        bmiLabel.textProperty().bind(bmi.asString("BMI: %.1f"));
        categoryLabel.textProperty().bind(Bindings.createStringBinding(() -> categoryOf(bmi.get()), bmi));

        final VBox root = new VBox(10, weightLabel, weight, heightLabel, height, bmiLabel, categoryLabel);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - custom");
        stage.setScene(new Scene(root, 300, 230));
        stage.show();
    }

    private static String categoryOf(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else {
            return "Overweight";
        }
    }

    static class Main {
        void main(String[] args) {
            Application.launch(CustomBindingExample.class, args);
        }
    }
}
