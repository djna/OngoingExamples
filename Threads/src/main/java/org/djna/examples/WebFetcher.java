package org.djna.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebFetcher {

    private String urlSpec;
    private URL url;
    HttpURLConnection con;


    public static void main(String[] args) throws Exception {
        WebFetcher fetcher = new WebFetcher("http://example.com");
        fetcher.fetch();
    }

    private WebFetcher(String urlSpec){
        this.urlSpec = urlSpec;
    }

    private void fetch() throws Exception {
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

        System.out.printf("GET URL %s, status %d%n", urlSpec, status);
        System.out.printf("Content: %s%n", content.toString());
    }
}
