package com.github.aprofromindia.weatherapp.http;

import com.github.aprofromindia.weatherapp.entities.WeatherList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("v1/public/yql")
    Call<WeatherList> getWeatherList(@Query("q") String query,
                                     @Query("format") String format);
}
