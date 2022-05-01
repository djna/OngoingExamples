package org.djna.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TheFuture {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {

        TheFuture oneFuture = new TheFuture();
        Future<String> future = oneFuture.calculateAsync();
        System.out.printf("get result in %s ... %n", Thread.currentThread().getName());
        String result = future.get();
        System.out.printf("Future %s in %s%n", result, Thread.currentThread().getName());

        Future<String> instantFuture =
                CompletableFuture.completedFuture("Hello instantly");

        System.out.printf("get instant result in %s ... %n", Thread.currentThread().getName());
        System.out.printf("Future %s%n", instantFuture.get());

        CompletableFuture<String> asyncFuture =
                CompletableFuture.supplyAsync(
                        () -> {
                            System.out.println("async a bit ...");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String value = "HelloAsynch";
                            System.out.printf("returning on %s with %s%n",
                                    Thread.currentThread().getName(),
                                    value
                            );
                            return value;
                        }
                );

        System.out.printf("AsyncFuture %s%n", asyncFuture.get());

        asyncFuture.thenAccept( s -> {
            System.out.printf("accept AsyncFuture %s%n", s);
        });

    }

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        threadPool.submit(() -> {
            System.out.println("think a bit ...");
            Thread.sleep(5000);
            String value = "Hello";
            System.out.printf("Completing on %s with %s%n",
                    Thread.currentThread().getName(),
                    value
            );
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }
}
