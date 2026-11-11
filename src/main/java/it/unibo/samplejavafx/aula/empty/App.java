package it.unibo.samplejavafx.aula.empty;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void run(String[] args) {
        launch(args);
    }

    static class Main {
        void main(String[] args) {
            App.run(args);
        }
    }
}
