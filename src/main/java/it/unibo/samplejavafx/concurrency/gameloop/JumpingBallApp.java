package it.unibo.samplejavafx.concurrency.gameloop;

import it.unibo.samplejavafx.concurrency.gameloop.core.Ball;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class JumpingBallApp extends Application {
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final double TEXT_X = 10;
    private static final double TEXT_Y = 20;
    private static final double CANVAS_ORIGIN = 0;
    private static final double DIAMETER_MULTIPLIER = 2;

    private final Set<JumpingBallCommand> pressedCommands = new HashSet<>();
    private JumpingBallGame game;
    private RenderLoop<Ball> renderLoop;

    @Override
    public void start(Stage primaryStage) {
        setupGame();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = setupScene(canvas);
        setupInputHandlers(scene);
        primaryStage.setTitle("Jumping Ball Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        startRenderLoop(graphicsContext);
    }

    private void setupGame() {
        game = new JumpingBallGame();
        Thread gameThread = new Thread(game);
        gameThread.start();
    }

    private Scene setupScene(Canvas canvas) {
        StackPane root = new StackPane(canvas);
        return new Scene(root, WIDTH, HEIGHT);
    }

    private void setupInputHandlers(Scene scene) {
        scene.setOnKeyPressed(event -> {
            mapKeyCode(event.getCode()).ifPresent(pressedCommands::add);
            game.sendInput(new HashSet<>(pressedCommands));
        });
        scene.setOnKeyReleased(event -> {
            mapKeyCode(event.getCode()).ifPresent(pressedCommands::remove);
            game.sendInput(new HashSet<>(pressedCommands));
        });
    }

    private void startRenderLoop(GraphicsContext graphicsContext) {
        renderLoop = new RenderLoop<>(
            game.getOutputBuffer(),
            ball -> render(graphicsContext, ball)
        );
        new Thread(renderLoop).start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        game.stop();
        renderLoop.stopLoop();
    }

    private Optional<JumpingBallCommand> mapKeyCode(KeyCode keyCode) {
        return switch (keyCode) {
            case LEFT -> Optional.of(JumpingBallCommand.LEFT);
            case RIGHT -> Optional.of(JumpingBallCommand.RIGHT);
            case SPACE -> Optional.of(JumpingBallCommand.JUMP);
            default -> Optional.empty();
        };
    }

    private void render(GraphicsContext graphicsContext, Ball ball) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(CANVAS_ORIGIN, CANVAS_ORIGIN, WIDTH, HEIGHT);
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(
            ball.getPosition().x() - ball.getRadius(),
            ball.getPosition().y() - ball.getRadius(),
            ball.getRadius() * DIAMETER_MULTIPLIER,
            ball.getRadius() * DIAMETER_MULTIPLIER
        );
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText(
            "Press SPACE to jump, LEFT/RIGHT to move",
            TEXT_X,
            TEXT_Y
        );
    }

    static class Main {
        static void main(String[] args) {
            Application.launch(JumpingBallApp.class, args);
        }
    }
}
