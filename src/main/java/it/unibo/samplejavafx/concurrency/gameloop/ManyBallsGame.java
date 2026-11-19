package it.unibo.samplejavafx.concurrency.gameloop;

import it.unibo.samplejavafx.concurrency.gameloop.core.Ball;
import it.unibo.samplejavafx.concurrency.gameloop.core.GameLoop;
import it.unibo.samplejavafx.concurrency.gameloop.core.Vector2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManyBallsGame extends GameLoop<ManyBallsCommand, List<Ball>> {
    private static final int INITIAL_BALLS = 10;
    private static final double MIN_RADIUS = 10;
    private static final double RADIUS_VARIANCE = 20;
    private static final double MAX_SPEED = 200;
    private static final double SPEED_OFFSET = 0.5;
    private static final double CENTER_DIVISOR = 2;
    private static final double MILLIS_PER_SECOND = 1000.0;
    private static final long FRAME_TIME = 16;

    private List<Ball> balls;
    private final double width;
    private final double height;
    private final Random randomGenerator = new Random();

    public ManyBallsGame(double width, double height) {
        super(FRAME_TIME);
        this.width = width;
        this.height = height;
        balls = new ArrayList<>();
        for (int ballIndex = 0; ballIndex < INITIAL_BALLS; ballIndex++) {
            balls.add(createRandomBall());
        }
    }

    private Ball createRandomBall() {
        return new Ball(
            randomGenerator.nextDouble() * width,
            randomGenerator.nextDouble() * height,
            createRandomRadius(),
            createRandomSpeed(),
            createRandomSpeed()
        );
    }

    @Override
    protected void processInput(ManyBallsCommand input) {
        if (input == ManyBallsCommand.ADD_BALL) {
            addNewBall();
        }
    }

    private void addNewBall() {
        balls.add(new Ball(
            width / CENTER_DIVISOR,
            height / CENTER_DIVISOR,
            createRandomRadius(),
            createRandomSpeed(),
            createRandomSpeed()
        ));
    }

    private double createRandomRadius() {
        return MIN_RADIUS + randomGenerator.nextDouble() * RADIUS_VARIANCE;
    }

    private double createRandomSpeed() {
        return (randomGenerator.nextDouble() - SPEED_OFFSET) * MAX_SPEED;
    }

    @Override
    protected List<Ball> logic(float deltaTimeMillis) {
        double deltaTimeSeconds = deltaTimeMillis / MILLIS_PER_SECOND;
        updateBalls(deltaTimeSeconds);
        return balls;
    }

    private void updateBalls(double deltaTimeSeconds) {
        final List<Ball> nextBalls = new ArrayList<>();
        for (Ball ball : balls) {
            Ball nextBall = ball.updatePosition(deltaTimeSeconds);
            nextBalls.add(bounceBall(nextBall));
        }
        balls = nextBalls;
    }

    private Ball bounceBall(Ball ball) {
        double positionX = ball.getPosition().x();
        double positionY = ball.getPosition().y();
        double velocityX = ball.getVelocity().x();
        double velocityY = ball.getVelocity().y();
        double radius = ball.getRadius();
        if (positionX < radius || positionX > width - radius) {
            velocityX = -velocityX;
            positionX = Math.clamp(positionX, radius, width - radius);
        }
        if (positionY < radius || positionY > height - radius) {
            velocityY = -velocityY;
            positionY = Math.clamp(positionY, radius, height - radius);
        }
        return new Ball(
            new Vector2D(positionX, positionY),
            radius,
            new Vector2D(velocityX, velocityY)
        );
    }
}
