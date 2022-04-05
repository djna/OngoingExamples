package org.djna.streams;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;


public class Builder {

    public static void main(String[] args) {
        badStyle();
        goodStyle();
    }

    private static void badStyle(){

        File file = new File("words.txt");
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(file).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        } catch (Exception e) {
            System.out.printf("exception %s%n", e);
        }
        System.out.printf("Freq %s%n", freq);

    }

    private static void goodStyle(){

        File file = new File("words.txt");
        Map<String, Long> freq;
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq = words.collect(
                    groupingBy( String::toLowerCase, java.util.stream.Collectors.counting() )
            );
            System.out.printf("Freq %s%n", freq);
        } catch (Exception e) {
            System.out.printf("exception %s%n", e);
        }


    }
}

