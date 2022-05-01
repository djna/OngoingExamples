package org.djna.examples;

public class DodgyRunnable implements Runnable {
    private static DodgyCounter dodgy = new DodgyCounter();
    private int result;

    @Override
    public void run() {
        dodgy.increment();
        result = dodgy.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int goodCount = 0;
        for (int i = 0; i < 100_000L; i++) {
            DodgyRunnable workerA = new DodgyRunnable();
            DodgyRunnable workerB = new DodgyRunnable();
            Thread threadA = new Thread(workerA);
            Thread threadB = new Thread(workerB);

            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();

            if (workerA.result != workerB.result) {
                goodCount++;
            }
        }
        System.out.printf("Complete, good count %d%n", goodCount);

    }
}
