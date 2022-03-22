package org.djna.anagram;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Anagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);
        try (Stream<String> words
                     = Files.lines(dictionary)
            ) {
            words.flatMap(Pattern.compile(" ")::splitAsStream)
                    .map( w -> w.replaceAll("[^a-zA-Z ]", "")
                                .toLowerCase())
                    .sorted().distinct()
                    .collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    // Joshua, Bloch. Effective Java (p. 204). Pearson Education. Kindle Edition.
}

//Joshua, Bloch. Effective Java (p. 205). Pearson Education. Kindle Edition.