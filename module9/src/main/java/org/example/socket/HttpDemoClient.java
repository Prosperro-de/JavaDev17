package org.example.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpDemoClient {
    public byte[] getImageByteArray(String host, String path) throws IOException, URISyntaxException {
        URI uri = new URI("https", host, path, null);

        URL url = uri.toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Bad request"); // map to 4xx or 5xx
        }

        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream())) {
            return bufferedInputStream.readAllBytes();
        }
    }

}
