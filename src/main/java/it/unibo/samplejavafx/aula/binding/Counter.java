package it.unibo.samplejavafx.aula.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// A model with a JavaFX property: getCount(), setCount(int) and countProperty()
public class Counter {
    private final IntegerProperty count = new SimpleIntegerProperty(0);

    public int getCount() {
        return count.get();
    }

    public void setCount(int value) {
        count.set(value);
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void increment() {
        setCount(getCount() + 1);
    }

    public void decrement() {
        setCount(getCount() - 1);
    }
}
