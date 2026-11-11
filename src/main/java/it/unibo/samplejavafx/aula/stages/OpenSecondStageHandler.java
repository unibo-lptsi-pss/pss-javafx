package it.unibo.samplejavafx.aula.stages;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class OpenSecondStageHandler implements EventHandler<MouseEvent> {
    private final TextField inputField;

    public OpenSecondStageHandler(final TextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void handle(final MouseEvent event) {
        new SecondStage(inputField.getText()).show();
    }
}
