package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetRequest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://baike.baidu.com/search/word?word=HTTP"))
                .GET()
                .build();
        HttpResponse.BodyHandler<String> bh = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, bh);
        System.out.println("Status: "+response.statusCode());
        System.out.println("Header: "+response.headers());
        System.out.println("Body: "+response.body());
    }
}
