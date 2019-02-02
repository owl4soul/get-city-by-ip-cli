package com.github.owl4soul.getcitybyipcli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main {

    //todo
    //

    //todo убрать горячие клавишы из ubuntu которые исп-ся в IDEA (Ctrl+Shift+L)

    public static void main(String[] args) throws IOException {
        String ip = args[0];

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet("http://ip-api.com/json/" + ip);

            //System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response1 -> {
                int status = response1.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response1.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    System.out.println("server return status code " + status);
                    return "";
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            //System.out.println(responseBody);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(responseBody);
            String prettyJsonString = gson.toJson(je);

            System.out.println(prettyJsonString);
        }

    }

}
