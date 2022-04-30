package org.djna.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebFetcher {

    public static void main(String[] args) throws Exception {
        String urlSpec = "http://example.com";
        String page = getWebPage(urlSpec);
        System.out.printf("Url=%s%n, page=%s%n", urlSpec, page);
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
