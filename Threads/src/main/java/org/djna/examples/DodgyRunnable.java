package org.djna.examples;

public class DodgyRunnable implements Runnable {
    private static DodgyCounter dodgy;


    @Override
    public void run() {
        dodgy.increment();
    }

    public static void main(String[] args) throws InterruptedException {
        int goodCount = 0;
        for (int i = 0; i < 10_000L; i++) {
            dodgy = new DodgyCounter();
            DodgyRunnable workerA = new DodgyRunnable();
            DodgyRunnable workerB = new DodgyRunnable();
            Thread threadA = new Thread(workerA);
            Thread threadB = new Thread(workerB);

            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();

            if (dodgy.get() != 2) {
                //System.out.printf("%d failed%n", i);
            } else {
                goodCount++;
            }
        }
        System.out.printf("Complete, good count %d%n", goodCount);

    }
}
