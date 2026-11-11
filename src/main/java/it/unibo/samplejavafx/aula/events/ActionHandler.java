package it.unibo.samplejavafx.aula.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

// General event handler for action events
public class ActionHandler implements EventHandler<ActionEvent> {
    private final Label lbl;

    public ActionHandler(Label lbl) {
        this.lbl = lbl;
    }

    @Override
    public void handle(ActionEvent event) {
        lbl.setText("Hello, JavaFX World!");
    }
}
