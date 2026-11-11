package it.unibo.samplejavafx.aula.binding;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListBindingExample extends Application {
    @Override
    public void start(Stage stage) {
        final ObservableList<String> items = FXCollections.observableArrayList("Alice", "Bob");
        final ListView<String> list = new ListView<>(items);
        final TextField input = new TextField();
        final Button add = new Button("Add");
        final Button remove = new Button("Remove selected");
        final Label size = new Label();

        // bindings work on observable collections too
        size.textProperty().bind(Bindings.size(items).asString("%d elements"));
        add.disableProperty().bind(input.textProperty().isEmpty());
        remove.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());

        add.setOnAction(event -> {
            items.add(input.getText());
            input.clear();
        });
        remove.setOnAction(event -> items.remove(list.getSelectionModel().getSelectedItem()));

        final VBox root = new VBox(10, new HBox(10, input, add), list, remove, size);
        root.setPadding(new Insets(10));
        stage.setTitle("Binding - lists");
        stage.setScene(new Scene(root, 300, 350));
        stage.show();
    }

    static class Main {
        void main(String[] args) {
            Application.launch(ListBindingExample.class, args);
        }
    }
}
