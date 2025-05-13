package com.pirgeo.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ClientTrafficGenerator {
    private static final Random random = new Random();
    private static final String BASE_URL = "http://localhost:8080/order/%s";

    public static void main(String[] args) {
        while (true) {

            try {
                // simulate someone ordering a beverage every second
                orderBeverage(pickBeverage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static String pickBeverage() {
        double randomNumber = random.nextDouble();
        if (randomNumber < 0.2) {
            // 20% of people order hot chocolate
            return "hot-chocolate";
        } else if (randomNumber < 0.5) {
            // 30% of people order espresso
            return "espresso";
        } else {
            // 50% order cappuccino
            return "cappuccino";
        }
    }

    private static String orderBeverage(String beverage) throws IOException {
        URL url = new URL(String.format(BASE_URL, beverage));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        System.out.println("Ordering beverage: " + beverage);
        int status = con.getResponseCode();
        System.out.println("Status Code (" + url.toString() + "): " + status);

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