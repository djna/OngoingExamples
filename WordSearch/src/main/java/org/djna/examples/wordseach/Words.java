package org.djna.examples.wordseach;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Words {

    private ArrayList<String> words;

    public Words(Stream<String> sourceStream ){
        words = new ArrayList<>();
        sourceStream.forEach(
                (word) -> words.add(word)
        );
    }

    public Integer simpleSearch(String lookFor){
        return words.indexOf(lookFor);
    }

    public Integer binaryChopSearch(String lookFor){
        int rangeStart = 0;
        int rangeEnd = words.size() -1;

        // search code here

        return -1;
    }

    @Override
    public String toString() {
        return "Words{" +
                "words=" + words.size() +
                '}';
    }
}
