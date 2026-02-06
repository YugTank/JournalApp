package com.example.journalApp.service;

import com.example.journalApp.api.response.WeatherResponse;
import com.example.journalApp.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        String cacheKey="weather_of_"+city;
        WeatherResponse weatherResponse = redisService.get(cacheKey, WeatherResponse.class);
        if(weatherResponse!=null){
            log.info("Weather responsed recieved for city {} from Redis",city);
            return weatherResponse;
        }
        try {
            String url = appCache.APP_CACHE.get("weather_api")
                                        .replace("<api_key>", apiKey).replace("<city>", city);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
            if (response != null) {
                redisService.set(cacheKey, response.getBody(), 300L);
                log.info("Weather data for {} cached successfully", city);
            }
            return response.getBody();
        }
        catch (Exception e) {
            log.error("Error getting weather response for city {}",city);
            return null;
        }
    }
}
