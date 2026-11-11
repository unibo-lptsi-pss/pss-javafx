package it.unibo.samplejavafx.aula.events;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

// Handler for mouse click events
public class ClickHandler implements EventHandler<MouseEvent> {
    private final Label lbl;

    public ClickHandler(Label lbl) {
        this.lbl = lbl;
    }

    @Override
    public void handle(MouseEvent event) {
        lbl.setText("Hello, JavaFX World!");
    }
}
