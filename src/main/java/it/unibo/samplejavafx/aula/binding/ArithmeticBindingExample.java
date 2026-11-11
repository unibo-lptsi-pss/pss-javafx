package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArithmeticBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final Slider a = new Slider(0, 10, 2);
        final Slider b = new Slider(0, 10, 3);
        final Label sum = new Label();
        final Label product = new Label();
        final Label average = new Label();
        final Label max = new Label();

        // fluent API: the result of add is itself observable
        final NumberBinding aPlusB = a.valueProperty().add(b.valueProperty());
        sum.textProperty().bind(
            Bindings.format("%.1f + %.1f = %.1f", a.valueProperty(), b.valueProperty(), aPlusB));
        product.textProperty().bind(
            Bindings.format("%.1f * %.1f = %.1f", a.valueProperty(), b.valueProperty(),
                a.valueProperty().multiply(b.valueProperty())));
        // bindings can be combined further: (a + b) / 2
        average.textProperty().bind(aPlusB.divide(2).asString("average = %.1f"));
        // static helpers in Bindings
        max.textProperty().bind(
            Bindings.max(a.valueProperty(), b.valueProperty()).asString("max = %.1f"));

        final VBox root = new VBox(10, new Label("a"), a, new Label("b"), b, sum, product, average, max);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - arithmetic");
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(ArithmeticBindingExample.class, args);
        }
    }
}
