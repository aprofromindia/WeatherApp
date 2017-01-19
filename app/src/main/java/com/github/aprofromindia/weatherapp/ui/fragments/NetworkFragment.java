package com.github.aprofromindia.weatherapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.aprofromindia.weatherapp.R;
import com.github.aprofromindia.weatherapp.entities.WeatherList;
import com.github.aprofromindia.weatherapp.functional.Consumer;
import com.github.aprofromindia.weatherapp.http.RestClient;
import com.github.aprofromindia.weatherapp.http.WeatherService;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFragment extends Fragment {

    public static final String TAG = NetworkFragment.class.getSimpleName();
    private WeatherService service;
    private WeatherList weatherList;
    private Consumer<WeatherList> getConsumer;

    public static NetworkFragment newInstance(File cacheDir) {

        Bundle args = new Bundle();
        NetworkFragment fragment = new NetworkFragment();
        fragment.service = RestClient.getInstance(cacheDir)
                .createService(WeatherService.class);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void getWeatherList(final Consumer<WeatherList> consumer) {
        getConsumer = consumer;
        if (weatherList == null) {
            Call<WeatherList> call = service.getWeatherList(RestClient.WEATHER_YQL
                    , RestClient.JSON_FORMAT);
            call.enqueue(new Callback<WeatherList>() {
                @Override
                public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                    if (response.isSuccessful()) {
                        weatherList = response.body();
                        getConsumer.apply(weatherList);
                    } else {
                        showNetError();
                    }
                }

                @Override
                public void onFailure(Call<WeatherList> call, Throwable t) {
                    showNetError();
                }
            });
        } else {
            getConsumer.apply(weatherList);
        }
    }

    public void setWeatherList(@Nullable WeatherList weatherList) {
        this.weatherList = weatherList;
    }

    private void showNetError() {
        getConsumer.apply(null);
        Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_LONG).show();
    }
}
