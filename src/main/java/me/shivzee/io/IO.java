package me.shivzee.io;

import me.shivzee.util.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IO {
    public static Response requestGET(String baseUrl){
        try{
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept" , "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }

            reader.close();
            return new Response(connection.getResponseCode() , builder.toString().trim());
        } catch (Exception e){
            return new Response(101 , "Exception Caught "+e.getMessage());
        }
    }
}
