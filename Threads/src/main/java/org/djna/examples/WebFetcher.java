package org.djna.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

        Thread fetcherThread = new Thread(fetcherRunnable);
        System.out.printf("Starting%n");
        fetcherThread.start();
        System.out.printf("Joining%n");
        fetcherThread.join();
        System.out.printf("Joined%n");

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
