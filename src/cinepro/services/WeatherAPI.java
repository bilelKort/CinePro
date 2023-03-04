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
import java.time.LocalDate;
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
private static final String API_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public static String getWeatherData(String city, LocalDate date) throws Exception {
      String apiUrl = API_URL + "?q=" + city + "&appid=" + API_KEY;
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

    // Parse the response and filter for the data for the date you're interested in
    JSONObject obj = new JSONObject(response.toString());
    JSONArray list = obj.getJSONArray("list");
    for (int i = 0; i < list.length(); i++) {
        JSONObject item = list.getJSONObject(i);
        LocalDateTime itemTime = LocalDateTime.ofEpochSecond(item.getLong("dt"), 0, ZoneOffset.UTC);
        LocalDate itemDate = itemTime.toLocalDate();
        if (itemDate.equals(date)) {
            return item.toString();
        }
    }

    return null; // No data found for the specified date
}
    
     public static String displayWeather(LocalDate date, String city) throws Exception {
        String weatherData = getWeatherData(city, date);
          if (weatherData == null) {
        return "No weather data available for the specified time.";
    }
    JSONObject obj = new JSONObject(weatherData);
        JSONArray weatherArray = obj.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String main = weather.getString("main");
        JSONObject mainObj = obj.getJSONObject("main");
        double temp = mainObj.getDouble("temp");
        double celsius = temp - 273.15;
        
        
    String iconUrl;
    switch (main) {
        case "Thunderstorm":
            iconUrl = "https://openweathermap.org/img/w/11d.png";
            break;
        case "Drizzle":
        case "Rain":
            iconUrl = "https://openweathermap.org/img/w/10d.png";
            break;
        case "Snow":
            iconUrl = "https://openweathermap.org/img/w/13d.png";
            break;
        case "Mist":
        case "Smoke":
        case "Haze":
        case "Dust":
        case "Fog":
        case "Sand":
        case "Ash":
        case "Squall":
        case "Tornado":
            iconUrl = "https://openweathermap.org/img/w/50d.png";
            break;
        default:
            iconUrl = "https://openweathermap.org/img/w/01d.png";
            break;
    }
    
    return "Weather information for " + city + " at " + date .toString() + ":\n"
            + "Condition: " + main + "\n"
            + "Temperature: " + String.format("%.1f", celsius) + " \u00B0C\n"
            ;
    }
}
