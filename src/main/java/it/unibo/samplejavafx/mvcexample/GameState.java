package it.unibo.samplejavafx.mvcexample;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;

/**
 * A saved game, loaded from a CSV resource on the classpath.
 * Empty lastGuess and lastResult fields represent a game with no previous guess.
 */
public record GameState(int min, int max, int attempts, int remainingAttempts,
        int numberToGuess, Optional<Integer> lastGuess, Optional<DrawResult> lastResult) {

    public GameState {
        if (min > max || (long) max - min >= Integer.MAX_VALUE || attempts <= 0
                || remainingAttempts < 0 || remainingAttempts > attempts
                || numberToGuess < min || numberToGuess > max
                || lastGuess.isPresent() != lastResult.isPresent()
                || lastGuess.filter(guess -> guess < min || guess > max).isPresent()) {
            throw new IllegalArgumentException("Invalid game state");
        }
    }

    /**
     * Loads the bundled CSV through a stream, so it also works inside the shadow JAR.
     * @return the saved game state
     * @throws IOException if the resource is missing or malformed
     */
    public static GameState loadResource() throws IOException {
        // The leading slash resolves the path from the classpath root.
        final var stream = GameState.class.getResourceAsStream(
            "/it/unibo/samplejavafx/mvcexample/game-state.csv"
        );
        if (stream == null) {
            throw new IOException("Missing resource: game-state.csv");
        }
        try (var reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return loadCsv(reader);
        }
    }

    static GameState loadCsv(final Reader reader) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setTrim(true)
            .get();
        try (var parser = format.parse(reader)) {
            final var records = parser.getRecords();
            if (records.size() != 1) {
                throw new IOException("Expected one game state in game-state.csv");
            }
            final var row = records.getFirst();
            if (parser.getHeaderNames().size() != 7 || !row.isConsistent()) {
                throw new IOException("Expected seven columns in game-state.csv");
            }
            return new GameState(Integer.parseInt(row.get("min")), Integer.parseInt(row.get("max")),
                Integer.parseInt(row.get("attempts")), Integer.parseInt(row.get("remainingAttempts")),
                Integer.parseInt(row.get("numberToGuess")),
                row.get("lastGuess").isEmpty() ? Optional.empty() : Optional.of(Integer.parseInt(row.get("lastGuess"))),
                row.get("lastResult").isEmpty() ? Optional.empty() : Optional.of(DrawResult.valueOf(row.get("lastResult"))));
        } catch (IllegalArgumentException exception) {
            throw new IOException("Invalid headers or values in game-state.csv", exception);
        }
    }
}
