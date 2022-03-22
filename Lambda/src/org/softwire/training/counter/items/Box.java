package org.softwire.training.counter.items;

import org.softwire.training.counter.Countable;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Countable> implements Countable {
    private List<T> contents = new ArrayList<T>();

    public void add(T item) {
        contents.add(item);
    }

    public int getCount() {
        return contents.stream().mapToInt(Countable::getCount).sum();
    }
}
