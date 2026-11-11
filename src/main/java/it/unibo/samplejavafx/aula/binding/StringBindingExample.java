package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StringBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final TextField name = new TextField("Ada");
        final TextField surname = new TextField("Lovelace");
        final Label fullName = new Label();
        final Label greeting = new Label();
        final Label letters = new Label();

        // concat builds a new observable string
        fullName.textProperty().bind(name.textProperty().concat(" ").concat(surname.textProperty()));
        // like String.format, but recomputed on every change
        greeting.textProperty().bind(Bindings.format("Hello, %s %s!", name.textProperty(), surname.textProperty()));
        // from strings to a number, and back to a string
        letters.textProperty().bind(
            name.textProperty().length().add(surname.textProperty().length()).asString("Letters: %d"));

        final VBox root = new VBox(10, name, surname, fullName, greeting, letters);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - strings");
        stage.setScene(new Scene(root, 300, 220));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(StringBindingExample.class, args);
        }
    }
}
