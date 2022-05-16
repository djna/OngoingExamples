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


        boolean finished = false;
        while (! finished  ) {
            try {
                allFutures.get(1,TimeUnit.SECONDS);
                finished = true;
                System.out.printf("complete %n ");
            } catch ( TimeoutException te) {
                System.out.printf(". ");
            }
        }

        allFutures.whenComplete((result, error) -> {
            if (error == null) {
                System.out.printf("%nAll pages retrieved %n");
                int count = 0, total = 0;
                for (CompletableFuture<Integer> f: futureList){
                    try {
                        count++;
                        total += f.get();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("%d pages, total size %d  %n", count, total );
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
        //System.out.printf("Fetching in %s%n", Thread.currentThread().getName());
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
