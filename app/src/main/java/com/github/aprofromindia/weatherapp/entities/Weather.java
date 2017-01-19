package com.github.aprofromindia.weatherapp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private int code;
    @JsonFormat(pattern = "www, dd MMM yyyy hh:mm a z TZ")
    private Date date;
    private int temp;
    @JsonProperty("text")
    private String condition;

    public Weather(int code, int temp, String condition) {
        this.code = code;
        this.temp = temp;
        this.condition = condition;
    }

    public int getCode() {
        return code;
    }

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
