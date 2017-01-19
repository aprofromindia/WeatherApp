package com.github.aprofromindia.weatherapp.entities.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.aprofromindia.weatherapp.entities.Weather;
import com.github.aprofromindia.weatherapp.entities.WeatherList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherListDeserializer extends JsonDeserializer<WeatherList> {

    @Override
    public WeatherList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        List<Weather> weatherList = new ArrayList<>();

        for (JsonNode n : node.path("query").path("results").path("channel")) {
            JsonNode weather = n.path("item").path("condition");
            final int code = weather.path("code").asInt();
            final int temp = weather.path("temp").asInt();
            final String condition = weather.path("text").asText();
            weatherList.add(new Weather(code, temp, condition));
        }
        return new WeatherList(weatherList);
    }
}
