package org.djna.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebFetcher {

    public static void main(String[] args) throws Exception {

        Runnable fetcherRunnable = () ->{
            System.out.printf("Fetching in %d%n", Thread.currentThread().getId());
            String urlSpec = "http://example.com";

            try {
                String page = getWebPage(urlSpec);
                System.out.printf("Url=%s%n, page=%s%n", urlSpec, page);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for ( int i = 0; i < 3 ; i++) {
            pool.execute(fetcherRunnable);
        }

    }

    private static String getWebPage(String urlSpec) throws Exception {
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
