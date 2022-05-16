package org.djna.examples;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class WorkRecord {
    class Statistics {};
    public Statistics getStatistics(){
        return null;
    }

    public void recordWork(int count, long timestamp){

    }

    public static void main(String[] args) {
       long start = new Date().getTime();
        pi(150_000);
        long end = new Date().getTime();
        System.out.printf("pi %,d%n", (end - start) );

        start = new Date().getTime();
        parallelPi(150_000);
        end = new Date().getTime();
        System.out.printf("parallelPi %,d%n", (end - start) );

    /*    final int parallelism = 4;
        ForkJoinPool forkJoinPool = null;
        try {
            forkJoinPool = new ForkJoinPool(parallelism);
            final long count = forkJoinPool.submit(() ->
                    // Parallel task here, for example
                    parallelPi(10_000_000)
            ).get();
            System.out.printf("Pool count %,d%n",count);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }*/
    }
    private static boolean probablyPrime(BigInteger i){
        return i.isProbablePrime(50);
    }

    static long pi(long n) {
        long count = LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter((i) -> i.isProbablePrime(50))
                .count();

        return count;
    }

    static long parallelPi(long n) {

        long count = LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .parallel()
                .filter((i) -> i.isProbablePrime(50))
                .count();

        return count;
    }


}
