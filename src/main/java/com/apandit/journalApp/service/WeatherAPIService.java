package com.apandit.journalApp.service;

import com.apandit.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherAPIService {
    private static final String apiKey = "a3c49a264fa44d2a021c6c0b8f7c2ea9";

    private static final String API = "https://api.weatherstack.com/current?access_key=API&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalAPI = API.replace("CITY", city).replace("API", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
