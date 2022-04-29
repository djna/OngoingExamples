package org.djna.examples;

import java.util.Random;

public class RunnableExample {


    public static void main(String[] args) {

        Runnable longRunningCalculation = () -> {

            try {
                long sleepFor = new Random().nextInt(5000) + 2000L;
                Thread.sleep(sleepFor);
                System.out.printf("Ping %d after %d%n", Thread.currentThread().getId(), sleepFor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        System.out.printf("Main in %d%n", Thread.currentThread().getId());
        Thread backgroundCalculationThread1 = new Thread(longRunningCalculation);
        Thread backgroundCalculationThread2 = new Thread(longRunningCalculation);
        backgroundCalculationThread1.start();
        backgroundCalculationThread2.start();
        try {
            System.out.printf("Wait for 1%n");
            backgroundCalculationThread1.join();
            System.out.printf("Joined 1%n");
            backgroundCalculationThread2.join();
            System.out.printf("Joined 2%n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
