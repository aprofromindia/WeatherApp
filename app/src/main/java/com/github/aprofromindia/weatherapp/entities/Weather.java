package com.github.aprofromindia.weatherapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.aprofromindia.weatherapp.entities.deserializers.CustomDateDeserializer;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;
    private int temp;
    @JsonProperty("text")
    private String condition;

    public Date getDate() {
        return date;
    }

    public int getTemp() {
        return temp;
    }

    public String getCondition() {
        return condition;
    }
}
