package org.softwire.training.counter;

import java.util.function.Predicate;

public class Counter<T extends Countable> implements Countable {
    private final Predicate<T> shouldCount;
    private int count;

    Counter(Predicate<T> shouldCount) {
        this.shouldCount = shouldCount;
    }

    Counter() {
        this.shouldCount = t -> true;
    }

    public int getCount() {
        return count;
    }

    public void add(T item) {
        if (shouldCount.test(item)) {
            count += item.getCount();
        }
    }
}
