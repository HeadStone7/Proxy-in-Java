package com.company;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AsyncTest {
    public static void main(String[] args) throws Exception{
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://baike.baidu.com/search/word"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("word=HTTP"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(resp ->new Object[]{resp.statusCode(), resp.body()})
                .thenAccept(rt ->{
                    System.out.println("POST REQUEST RESPONSE CODE"+rt[0]);
                    System.out.println("POST THE RESPONSE BODY OF THE REQUEST"+rt[1]);
                });
        System.out.println("--程序结束--");
        Thread.sleep(3000);
    }
}
