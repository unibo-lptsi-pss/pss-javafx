package it.unibo.samplejavafx.aula.layouts;

import java.time.Month;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LayoutsExample extends Application {
    @Override
    public void start(Stage stage) {
        final TabPane tabs = new TabPane(
            new Tab("Group", groupExample()),
            new Tab("BorderPane", borderPaneExample()),
            new Tab("AnchorPane", anchorPaneExample()),
            new Tab("TilePane", tilePaneExample()),
            new Tab("GridPane", gridPaneExample())
        );
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        stage.setTitle("JavaFX - Layouts");
        stage.setScene(new Scene(tabs, 500, 400));
        stage.show();
    }

    private static Parent groupExample() {
        Group g = new Group();
        Label l1 = new Label("label");
        Button b1 = new Button("a larger button");
        Button b2 = new Button("small button");
        g.getChildren().addAll(l1, b1, b2);
        // Use binding to suitable place the components
        b1.layoutXProperty().bind(l1.widthProperty().add(10));
        b2.layoutXProperty().bind(b1.layoutXProperty()
            .add(b1.widthProperty()).add(10));
        g.setTranslateX(-5); // applies translation to all children
        g.setEffect(new DropShadow()); // applies effect to all children
        return g;
    }

    private static Parent borderPaneExample() {
        Text center = new Text("Center");
        Text top = new Text("Top");
        Text right = new Text("Right");
        Text bottom = new Text("Bottom");
        Text left = new Text("Left");
        BorderPane bpane = new BorderPane(center, top, right, bottom, left);
        bpane.setCenter(new Text("NewCenter"));
        return bpane;
    }

    private static Parent anchorPaneExample() {
        Button topLeft = new Button("Top Left");
        AnchorPane.setTopAnchor(topLeft, 10.0); // 10px from the top edge
        AnchorPane.setLeftAnchor(topLeft, 10.0); // 10px from the left edge
        return new AnchorPane(topLeft);
    }

    private static Parent tilePaneExample() {
        // An empty vertical TilePane with 5px horiz / 10px vertical spacing
        TilePane tp2 = new TilePane(Orientation.VERTICAL, 5, 10);
        tp2.setPrefRows(3);
        tp2.setPrefTileHeight(100);
        for (Month m : Month.values()) {
            tp2.getChildren().add(new Label(m.name()));
        }
        return tp2;
    }

    private static Parent gridPaneExample() {
        GridPane gp = new GridPane();
        gp.setGridLinesVisible(true);
        for (Month m : Month.values()) {
            Label l = new Label(m.name());
            gp.getChildren().add(l);
            int columnIndex = (m.getValue() - 1) / 4;
            int rowIndex = (m.getValue() - 1) % 4;
            GridPane.setConstraints(l, columnIndex, rowIndex);
            // OR ALSO: gp.add(l, columnIndex, rowIndex);
        }
        return gp;
    }

    static class Main {
        void main(String[] args) {
            Application.launch(LayoutsExample.class, args);
        }
    }
}
