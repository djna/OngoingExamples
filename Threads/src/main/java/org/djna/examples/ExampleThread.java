package org.djna.examples;

import java.util.concurrent.TimeUnit;

public class ExampleThread {

    static void think() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished thinking");
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread backgroundThread = new Thread(
                ExampleThread::think
        );
        backgroundThread.start();

        while( backgroundThread.isAlive()) {
            System.out.println("Application waiting");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Application exiting");

    }



}

