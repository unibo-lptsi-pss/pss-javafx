package it.unibo.samplejavafx.mvcexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class GameStateTest {
    @Test
    void readsQuotedFieldsByHeaderName() throws IOException {
        final String csv = "max,min,attempts,remainingAttempts,numberToGuess,lastGuess,lastResult\n"
            + "\"100\",\"10\",5,5,42,\"\",\"\"\n";
        final GameState state = GameState.loadCsv(new StringReader(csv));

        assertEquals(10, state.min());
        assertEquals(100, state.max());
        assertEquals(Optional.empty(), state.lastGuess());
        assertEquals(Optional.empty(), state.lastResult());
    }

    @Test
    void rejectsMissingColumns() {
        assertThrows(IOException.class, () -> GameState.loadCsv(new StringReader("min,max\n10,100\n")));
    }

    @Test
    void restoresBundledGameAndContinuesPlaying() throws IOException {
        final GameState state = GameState.loadResource();
        final DrawNumberImpl model = new DrawNumberImpl(state);

        assertEquals(10, model.minProperty().getValue());
        assertEquals(100, model.maxProperty().getValue());
        assertEquals(5, model.attemptsProperty().getValue());
        assertEquals(3, model.remainingAttemptsProperty().getValue());
        assertEquals(Optional.of(60), model.lastGuessProperty().getValue());
        assertEquals(Optional.of(DrawResult.YOURS_HIGH), model.lastGuessResult().getValue());
        assertEquals(DrawResult.YOU_WON, model.attempt(42));
        assertEquals(2, model.remainingAttemptsProperty().getValue());

        model.reset();
        assertEquals(5, model.remainingAttemptsProperty().getValue());
    }
}
