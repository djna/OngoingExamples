package org.djna.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiWebFetcher {

    public static void main(String[] args) throws Exception {

        MultiWebFetcher aFetcher = new MultiWebFetcher();
        List<CompletableFuture> futureList = aFetcher.manyFutureFactory(100);

        CompletableFuture allFutures = CompletableFuture.allOf(
                futureList.toArray(new CompletableFuture[0])
        );


        boolean interrupted = false;
        while (! interrupted && allFutures.getNow("working") != null ) {
            try {
                System.out.printf(". ");
                Thread.sleep(100);
            } catch ( Exception te) {
                interrupted = true;
            }
        }

        allFutures.whenComplete((result, error) -> {
            if (error == null) {
                System.out.printf("%nAll pages retrieved %n");
                for (CompletableFuture f: futureList){
                    try {
                        System.out.printf("page %d %n", f.get() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.printf("%n Error %s%n", error.toString());
            }
        });

    }

    private List<CompletableFuture> manyFutureFactory(int howMany){
        List<CompletableFuture> theFutures = new ArrayList<>();
        for ( int i = 0 ; i < howMany; i++) {
            MultiWebFetcher aFetcher = new MultiWebFetcher();
            CompletableFuture future = aFetcher.fetcherFactory();
            theFutures.add(future);
        }
        return theFutures;
    }

    private CompletableFuture<Integer> fetcherFactory() {
        return CompletableFuture.supplyAsync(
                () -> {
                    return fetchPage();
                }
        );
    }

    private int fetchPage() {
        System.out.printf("Fetching in %s%n", Thread.currentThread().getName());
        String urlSpec = "http://example.com";
        int length = -1;

        try {
            String page = getWebPage(urlSpec);
            length = page.length();
            //System.out.printf("%nUrl=%s, page=%s%n", urlSpec, length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return length;
    }



    private String getWebPage(String urlSpec) throws Exception {
        URL url;
        HttpURLConnection con;
        url = new URL(urlSpec);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }
}
