package org.djna.examples;

import java.util.concurrent.TimeUnit;

public class HelloThread {

    private static void sayHello() {
        System.out.printf("Hello from %d%n", Thread.currentThread().getId());
    }

    public static void main(String[] args) throws Exception {

        Thread backgroundThread = new Thread(HelloThread::sayHello);
        backgroundThread.start();


    }
}



