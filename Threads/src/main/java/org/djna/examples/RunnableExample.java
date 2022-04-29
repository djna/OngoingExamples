package org.djna.examples;
public class RunnableExample {

    public static void main(String[] args) {

        Runnable longRunningCalculation = () -> {

            try {
                Thread.sleep(500);
                System.out.printf("Ping %d%n", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.printf("Main in %d%n", Thread.currentThread().getId());
        Thread backgroundCalculationThread1 = new Thread(longRunningCalculation);
        Thread backgroundCalculationThread2 = new Thread(longRunningCalculation);
        backgroundCalculationThread1.run();
        backgroundCalculationThread1.start();
        backgroundCalculationThread2.start();
    }
}
