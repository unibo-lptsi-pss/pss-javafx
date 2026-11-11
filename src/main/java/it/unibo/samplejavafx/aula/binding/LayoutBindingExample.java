package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LayoutBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final Pane pane = new Pane();
        final Circle circle = new Circle();
        circle.setFill(Color.ORANGE);
        final Line diagonal = new Line();

        // the circle stays in the middle of the window: try resizing it
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.radiusProperty().bind(Bindings.min(pane.widthProperty(), pane.heightProperty()).divide(4));
        // the line goes from the top-left to the bottom-right corner
        diagonal.endXProperty().bind(pane.widthProperty());
        diagonal.endYProperty().bind(pane.heightProperty());

        pane.getChildren().addAll(diagonal, circle);
        stage.setTitle("Binding - layout");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(LayoutBindingExample.class, args);
        }
    }
}
