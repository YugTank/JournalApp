package com.example.journalApp.api.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
public class WeatherResponse{
    private Request request;
    private Location location;
    private Current current;

    @Getter
    @Setter
    public class Current{
        private String observation_time;
        private int temperature;
        private int weather_code;
        private ArrayList<String> weather_icons;
        private ArrayList<String> weather_descriptions;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;
    }

    @Getter
    @Setter
    public class Location{
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezone_id;
        private String localtime;
        private int localtime_epoch;
        private String utc_offset;
    }

    @Getter
    @Setter
    public class Request{
        private String type;
        private String query;
        private String language;
        private String unit;
    }
}

