package it.unibo.samplejavafx.aula.stages;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public final void start(final Stage mainStage) {
        final Scene scene = new Scene(initSceneUI());
        mainStage.setScene(scene);
        mainStage.setTitle("JavaFX Example");
        mainStage.show();
    }

    private Parent initSceneUI() {
        final Label inputLbl = new Label("Input: ");
        final TextField inputArea = new TextField();
        final Button okBtn = new Button("Open a new Stage with the input data!");

        okBtn.setOnMouseClicked(new OpenSecondStageHandler(inputArea));
        final BorderPane root = new BorderPane();
        root.setRight(okBtn);
        root.setLeft(inputLbl);
        root.setCenter(inputArea);
        BorderPane.setAlignment(inputLbl, Pos.CENTER_LEFT);
        BorderPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

        return root;
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
