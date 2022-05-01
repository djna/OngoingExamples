package org.djna.examples;

import static java.lang.Thread.sleep;

class DodgyCounter {
    private volatile int count;

    public void increment() {
        int original = count;
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count = original + 1;
    }

    public int get() {
        return count;
    }

}
