/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kortb
 */
public class WeatherAPI {
      private static final String API_KEY = "d04124d3b05703647f177fbbe1ae781a";
      private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static String getWeatherData(String city, Timestamp timestamp) throws Exception {
        // Convert the Timestamp to a LocalDateTime object
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        
        // Convert LocalDateTime to Unix timestamp
        long unixTime = dateTime.toEpochSecond(ZoneOffset.UTC);

        // Build the API URL with the city and date parameters
        String apiUrl = String.format("%s?q=%s&dt=%d&appid=%s", API_URL, city, unixTime, API_KEY);

        // Make an API request and retrieve the response
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }
    
     public static String displayWeather(Timestamp time, String city) throws Exception {
        String weatherData = getWeatherData(city,time);
        JSONObject obj = new JSONObject(weatherData);
        JSONArray weatherArray = obj.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String main = weather.getString("main");
        JSONObject mainObj = obj.getJSONObject("main");
        double temp = mainObj.getDouble("temp");
        double celsius = temp - 273.15;
        return "Weather information for " + city + " at " + time.toString() + ":\n"
                + "Condition: " + main + "\n"
                + "Temperature: " + String.format("%.1f", celsius) + " \u00B0C\n";
    }
}
