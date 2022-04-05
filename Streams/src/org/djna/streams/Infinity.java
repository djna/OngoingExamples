package org.djna.streams;

import java.util.stream.Stream;

public class Infinity {
    private static int x = 0;
    static int makeInt(){
        System.out.printf("get %d%n", x);
        return x++;
    }
    public static void main(String[] args) {

     /*   File file = new File("words.txt");
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        } catch (Exception e) {
            System.out.printf("exception %s%n", e);
        }
        System.out.printf("Freq %s%n", freq);*/


        Stream<Integer> infiniteIntegers = Stream.generate(() -> makeInt() );
        long howMany = infiniteIntegers.limit(10).count();
        System.out.printf("got %d%n", howMany);

        infiniteIntegers.max(Integer::compare);




    }
}

