package it.unibo.samplejavafx.aula.stages;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SecondStage extends Stage {
    private Label lbl;

    public SecondStage(final String message) {
        super();
        setTitle("New Window...");
        setScene(new Scene(initSceneUI(), 400, 200));
        lbl.setText(message);
    }

    private Parent initSceneUI() {
        lbl = new Label();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(lbl);
        return root;
    }
}
