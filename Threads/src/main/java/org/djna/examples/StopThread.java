package org.djna.examples;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static boolean stopRequested;
    private static void doWork() {
        int i = 0;
        while (!stopRequested) {
            i++;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(StopThread::doWork);
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}



