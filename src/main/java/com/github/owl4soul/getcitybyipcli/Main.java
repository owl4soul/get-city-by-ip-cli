package com.github.owl4soul.getcitybyipcli;

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    //todo
    //

    //todo убрать горячие клавишы из ubuntu которые исп-ся в IDEA (Ctrl+Shift+L)

    public static void main(String[] args) throws Throwable {
        System.out.println("i work");
        String url = "http://ip-api.com/json/97.65.170.121";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }

}
