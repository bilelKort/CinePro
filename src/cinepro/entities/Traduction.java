/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 *
 * @author acer
 */
public class Traduction {
    public String traduire(String feedback) throws IOException {
    OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
String value = "{\"from\":\"en\",\"to\":\"fr\",\"e\":\"\",\"q\":[\"" + feedback + "\"]}";
RequestBody body = RequestBody.create(mediaType, value);
Request request = new Request.Builder()
	.url("https://rapid-translate-multi-traduction.p.rapidapi.com/t")
	.post(body)
	.addHeader("content-type", "application/json")
	.addHeader("X-RapidAPI-Key", "73144e7b2emsh6a25e66552f8578p149f24jsnbc2d27f5b046")
	.addHeader("X-RapidAPI-Host", "rapid-translate-multi-traduction.p.rapidapi.com")
	.build();
        
           
            ObjectMapper objectMapper = new ObjectMapper();
           ResponseBody responseBody = client.newCall(request).execute().body(); 
return  (String) objectMapper.readValue(responseBody.string(), ArrayList.class).get(0);
       

    }

}
