package com.apandit.journalApp.service;

import com.apandit.journalApp.api.response.WeatherResponse;
import com.apandit.journalApp.cache.ApplicationCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAPIService {
    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API = "https://api.weatherstack.com/current?access_key=API&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationCache applicationCache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = applicationCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
