package it.unibo.samplejavafx.aula.screens;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreensExample extends Application {
    @Override
    public void start(Stage stage) {
        Screen s = Screen.getPrimary();
        double dpi = s.getDpi();
        Rectangle2D sb = s.getBounds();
        Rectangle2D svb = s.getVisualBounds();

        ObservableList<Screen> screenList = Screen.getScreens();

        final VBox root = new VBox(
            new Label("DPI: " + dpi),
            new Label("Bounds: " + sb),
            new Label("Visual bounds: " + svb),
            new Label("Number of screens: " + screenList.size())
        );
        root.setPadding(new Insets(10));

        stage.setTitle("JavaFX - Screens");
        stage.setScene(new Scene(root));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(ScreensExample.class, args);
        }
    }
}
