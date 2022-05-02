package org.djna.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncWorker {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {

        CompletableFuture.supplyAsync(
                () -> { return "world"; }
        ).thenCompose(
                (id) ->   { return makeFutureGreeting(id);}
        ).thenApply(
                (g) -> {
                    System.out.printf("Greeting %s%n", g);
                    return "done";
                }
        ).join();
        //System.out.printf("result %s%n", result);

    }

    private static CompletableFuture<String> makeFutureGreeting(String subject){
        return CompletableFuture.supplyAsync(
                () -> { return makeGreetingEventually(subject);}
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
