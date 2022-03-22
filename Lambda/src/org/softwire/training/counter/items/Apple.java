package org.softwire.training.counter.items;

import org.softwire.training.counter.Countable;

public class Apple implements Countable {
    private Colour colour;

    public Apple(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    public int getCount() {
        return 1;
    }
}
