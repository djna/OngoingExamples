package org.djna.examples;

public class DodgyRunnable implements Runnable {
    private static DodgyCounter dodgy;
    private int result;

    @Override
    public void run() {
        dodgy.increment();
        result = dodgy.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int goodCount = 0;
        int badCount = 0;
        for (int i = 0; i < 100_000L; i++) {
            dodgy = new DodgyCounter();
            DodgyRunnable workerA = new DodgyRunnable();
            DodgyRunnable workerB = new DodgyRunnable();
            Thread threadA = new Thread(workerA);
            Thread threadB = new Thread(workerB);

            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();

            if (workerA.result == 2 || workerB.result == 2) {
                goodCount++;
            } else {
                badCount++;
            }
        }
        System.out.printf("Complete, good count %d bad count %d%n", goodCount, badCount);

    }
}
