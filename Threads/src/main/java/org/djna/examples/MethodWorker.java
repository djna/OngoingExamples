package org.djna.examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MethodWorker {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static String getGuestName() {
        System.out.printf("Determine who to greet on %s%n",
                Thread.currentThread().getName()
        );
        return "World";
    }

    private static String getGreeting(String name) {
        System.out.printf("Get Greeting on %s with %s%n",
                Thread.currentThread().getName(),
                name
        );
        return "Hello " + name;
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture.supplyAsync(
                        MethodWorker::getGuestName, threadPool
                ).thenApplyAsync(
                        MethodWorker::getGreeting, threadPool
                ).
                thenComposeAsync(
                        MethodWorker::makeFutureGreeting
                ).thenApply(
                        (g) -> {
                            System.out.printf("I am told to %s%n", g);
                            return "done";
                        }
                ).join();
        //System.out.printf("result %s%n", result);

    }

    private static CompletableFuture<String> makeFutureGreeting(String subject) {
        return CompletableFuture.supplyAsync(
                () -> {
                    return makeGreetingEventually(subject);
                }
        );
    }

    private static String makeGreetingEventually(String text) {

        System.out.println("think a bit ...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = "Greet with " + text;
        System.out.printf("Completing on %s with %s%n",
                Thread.currentThread().getName(),
                value
        );

        return value;
    }
}
