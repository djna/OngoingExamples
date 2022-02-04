package org.djna.examples.wordseach;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class SearchApp {

    private URL dataURL;
    private Words words;
    private String searchFor;
    private int iterations;

    public static void main(String[] args) throws Exception{
        String searchFor = args[0];
        int iterations = Integer.parseInt(args[1]);

        // no error handling, just allow Exception to be reported
        SearchApp theApp = new SearchApp(searchFor, iterations);

        System.out.printf("Searching %s%n", theApp);
    }

    @Override
    public String toString() {
        return "SearchApp{" +
                "dataURL=" + dataURL +
                ", words=" + words +
                '}';
    }

    private SearchApp(String searchFor, int iterations) throws Exception {
        this.searchFor = searchFor;
        this.iterations = iterations;
        this.dataURL = new URL(
                "https://github.com/CorndelWithSoftwire/SearchAlgorithms-Java/raw/master/words");

        InputStream inputStream = dataURL.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> stream = reader.lines();

        this.words = new Words(stream);

        long simpleTime = measure(words::simpleSearch, searchFor);
        System.out.printf("Simple search %,d%n", simpleTime);

        long binaryChopTime = measure(words::binaryChopSearch, searchFor);
        System.out.printf("Binary chop search %,d%n", binaryChopTime);
    }

    private long measure(java.util.function.Function<String, Integer> finder, String searchFor){
        long start = System.nanoTime();
        int index = -1;
        for( int i = 0; i < iterations; i++) {
            index = finder.apply(searchFor);
        }
        long end = System.nanoTime();
        System.out.printf("Found %s at %,d%n", searchFor, index);
        return end - start;
    }
}
