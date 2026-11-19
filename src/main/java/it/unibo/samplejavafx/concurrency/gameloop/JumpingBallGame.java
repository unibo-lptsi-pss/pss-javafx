package it.unibo.samplejavafx.concurrency.gameloop;

import it.unibo.samplejavafx.concurrency.gameloop.core.Ball;
import it.unibo.samplejavafx.concurrency.gameloop.core.GameLoop;
import it.unibo.samplejavafx.concurrency.gameloop.core.Vector2D;

import java.util.Set;

public class JumpingBallGame extends GameLoop<Set<JumpingBallCommand>, Ball> {
    private static final double EARTH_GRAVITY_METERS_PER_SECOND_SQUARED = 9.81;
    private static final double PIXELS_PER_METER = 100;
    private static final double GRAVITY =
        EARTH_GRAVITY_METERS_PER_SECOND_SQUARED * PIXELS_PER_METER;
    private static final double JUMP_STRENGTH = -500;
    private static final double MOVE_SPEED = 200;
    private static final double GROUND_Y = 500;
    private static final double BALL_RADIUS = 20;
    private static final double INITIAL_X = 400;
    private static final double INITIAL_VELOCITY = 0;
    private static final double ZERO_VERTICAL_VELOCITY = 0;
    private static final double ZERO_GRAVITY_HORIZONTAL_COMPONENT = 0;
    private static final double GROUND_TOLERANCE = 1.0;
    private static final double MILLIS_PER_SECOND = 1000.0;
    private static final long FRAME_TIME = 16;

    private Ball ball;

    public JumpingBallGame() {
        super(FRAME_TIME);
        ball = new Ball(
            INITIAL_X,
            GROUND_Y,
            BALL_RADIUS,
            INITIAL_VELOCITY,
            INITIAL_VELOCITY
        );
    }

    @Override
    protected void processInput(Set<JumpingBallCommand> input) {
        handleMovement(input);
        handleJump(input);
    }

    private void handleMovement(Set<JumpingBallCommand> input) {
        double horizontalVelocity = INITIAL_VELOCITY;
        if (input.contains(JumpingBallCommand.LEFT)) {
            horizontalVelocity -= MOVE_SPEED;
        }
        if (input.contains(JumpingBallCommand.RIGHT)) {
            horizontalVelocity += MOVE_SPEED;
        }
        ball = ball.updateVelocity(
            new Vector2D(horizontalVelocity, ball.getVelocity().y())
        );
    }

    private void handleJump(Set<JumpingBallCommand> input) {
        if (input.contains(JumpingBallCommand.JUMP) && isBallOnGround()) {
            ball = ball.updateVelocity(
                new Vector2D(ball.getVelocity().x(), JUMP_STRENGTH)
            );
        }
    }

    private boolean isBallOnGround() {
        return Math.abs(ball.getPosition().y() - GROUND_Y) < GROUND_TOLERANCE;
    }

    @Override
    protected Ball logic(float deltaTimeMillis) {
        double deltaTimeSeconds = deltaTimeMillis / MILLIS_PER_SECOND;
        applyGravity(deltaTimeSeconds);
        moveBall(deltaTimeSeconds);
        checkGroundCollision();
        return ball;
    }

    private void applyGravity(double deltaTimeSeconds) {
        Vector2D gravityVelocity = new Vector2D(
            ZERO_GRAVITY_HORIZONTAL_COMPONENT,
            GRAVITY * deltaTimeSeconds
        );
        Vector2D velocity = ball.getVelocity().add(gravityVelocity);
        ball = ball.updateVelocity(velocity);
    }

    private void moveBall(double deltaTimeSeconds) {
        ball = ball.updatePosition(deltaTimeSeconds);
    }

    private void checkGroundCollision() {
        if (ball.getPosition().y() > GROUND_Y) {
            ball = new Ball(
                new Vector2D(ball.getPosition().x(), GROUND_Y),
                ball.getRadius(),
                new Vector2D(ball.getVelocity().x(), ZERO_VERTICAL_VELOCITY)
            );
        }
    }
}
