package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BooleanBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final TextField user = new TextField();
        user.setPromptText("Username");
        final PasswordField password = new PasswordField();
        password.setPromptText("Password");
        final PasswordField confirm = new PasswordField();
        confirm.setPromptText("Confirm password");
        final CheckBox terms = new CheckBox("I accept the terms");
        final Label error = new Label("Passwords do not match");
        error.setTextFill(Color.RED);
        final Button register = new Button("Register");

        final BooleanBinding passwordsDiffer = password.textProperty().isNotEqualTo(confirm.textProperty());
        // the error is visible only while the passwords differ
        error.visibleProperty().bind(passwordsDiffer);
        // the button is enabled only when every condition holds
        register.disableProperty().bind(
            user.textProperty().isEmpty()
                .or(password.textProperty().isEmpty())
                .or(passwordsDiffer)
                .or(terms.selectedProperty().not())
        );

        final VBox root = new VBox(10, user, password, confirm, error, terms, register);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - booleans");
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(BooleanBindingExample.class, args);
        }
    }
}
