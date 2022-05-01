package org.djna.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TheFuture {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

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

    public static void main(String[] args) throws Exception {

        TheFuture oneFuture = new TheFuture();
        Future<String> future = oneFuture.calculateAsync();
        System.out.printf("get result in %s ... %n", Thread.currentThread().getName());
        String result = future.get();
        System.out.printf("Future %s in %s%n", result, Thread.currentThread().getName() );

    }
}
