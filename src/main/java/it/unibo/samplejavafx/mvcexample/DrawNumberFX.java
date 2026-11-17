package it.unibo.samplejavafx.mvcexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.stage.Stage;

/**
 * JavaFX application for the "draw number" game.
 */
public final class DrawNumberFX extends Application implements DrawNumberViewObserver {
    private DrawNumberObservable model;
    private List<DrawNumberView> views;

    @Override
    public void init() throws IOException {
        model = new DrawNumberImpl(GameState.loadResource());
        views = new ArrayList<>();
        views.addAll(Arrays.asList(
            new DrawNumberViewImpl(model, new BoundingBox(0, 0, 0, 0)),
            new DrawNumberViewImpl(model, null)
        ));
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    public static void run(final String... args) {
        launch(args);
    }
}
