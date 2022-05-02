package org.djna.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TheWorker {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {

        CompletableFuture<String> asyncFuture =
                CompletableFuture.supplyAsync(
                        () -> {
                            return makeGreetingEventually("dave");
                        }
                );

        System.out.printf("AsyncFuture %s%n", asyncFuture.get());

        asyncFuture.thenAccept(s -> {
            System.out.printf("accept AsyncFuture on %s %s%n", Thread.currentThread().getName(), s);
        }).thenRun(
                () -> {
                    System.out.printf("Complete on %s %n", Thread.currentThread().getName());
                }
        );

    }

    private static String makeGreetingEventually(String subject) {

        System.out.println("think a bit ...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = "Hello " + subject;
        System.out.printf("Completing on %s with %s%n",
                Thread.currentThread().getName(),
                value
        );

        return value;
    }
}
