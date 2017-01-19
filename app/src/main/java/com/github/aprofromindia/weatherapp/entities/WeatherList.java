package com.github.aprofromindia.weatherapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.aprofromindia.weatherapp.entities.deserializers.WeatherListDeserializer;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = WeatherListDeserializer.class)
public class WeatherList {

    private List<Weather> weatherList;

    public WeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }
}
