package org.djna.examples;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

class DodgyCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public synchronized void  increment() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }

}
