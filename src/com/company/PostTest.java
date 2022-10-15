package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://baike.baidu.com/search/word"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("word=HTTP"))
                .build();
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST status: "+response.statusCode());
        System.out.println("POST Body: "+ response.body());
    }
}
