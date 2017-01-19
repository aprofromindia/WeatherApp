package com.github.aprofromindia.weatherapp.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.aprofromindia.weatherapp.R;
import com.github.aprofromindia.weatherapp.databinding.ItemWeatherBinding;
import com.github.aprofromindia.weatherapp.entities.Weather;

import java.util.Collections;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<Weather> weatherList = Collections.emptyList();

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWeatherBinding binding = ItemWeatherBinding.inflate(inflater, parent, false);
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.binding.setWeather(weatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {

        ItemWeatherBinding binding;

        WeatherViewHolder(ItemWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
