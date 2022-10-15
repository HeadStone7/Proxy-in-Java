package com.company;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProxySelectorT {
    public static void main(String[] args) throws IOException {
        final String PROXY_ADDR = "60.247.131.64";
        final int PROXY_PORT = 80;
        String urlStr = "http://aaajy.net";

        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                List<Proxy> result = new ArrayList<Proxy>();
                result.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT)));
                return result;
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("Connection failed");
            }
        });

        URL url = new URL(urlStr);

        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(3000);
        Scanner scan = new Scanner(conn.getInputStream());
        PrintStream ps = new PrintStream("index.html");

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);
            ps.println(line);
        }
    }
}
