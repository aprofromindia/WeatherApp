package com.github.aprofromindia.weatherapp.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.github.aprofromindia.weatherapp.R;
import com.github.aprofromindia.weatherapp.databinding.ActivityMainBinding;
import com.github.aprofromindia.weatherapp.entities.WeatherList;
import com.github.aprofromindia.weatherapp.functional.Consumer;
import com.github.aprofromindia.weatherapp.ui.adapters.WeatherAdapter;
import com.github.aprofromindia.weatherapp.ui.fragments.NetworkFragment;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private WeatherAdapter adapter = new WeatherAdapter();
    private NetworkFragment networkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        setupViews(binding);
        setupNetworkFragment();
    }

    private void setupViews(ActivityMainBinding binding) {
        final RecyclerView recyclerView = binding.recyclerView;
        refreshLayout = binding.refreshLayout;
        progressBar = binding.progressBar;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new
//                WeatherItemDecorator(ContextCompat.getDrawable(this, 0)));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((WeatherAdapter) recyclerView.getAdapter()).setWeatherList(null);
                getWeatherList(networkFragment);
            }
        });
        refreshLayout.setColorSchemeColors(ContextCompat
                .getColor(this, R.color.colorAccent));
    }

    private void setupNetworkFragment() {
        networkFragment = (NetworkFragment) getSupportFragmentManager()
                .findFragmentByTag(NetworkFragment.TAG);

        if (networkFragment == null) {
            networkFragment = NetworkFragment.newInstance(getCacheDir());
            getSupportFragmentManager().beginTransaction()
                    .add(networkFragment, NetworkFragment.TAG)
                    .commit();
        }
        getWeatherList(networkFragment);
    }

    private void getWeatherList(NetworkFragment networkFragment) {
        networkFragment.getWeatherList(new Consumer<WeatherList>() {
            @Override
            public void apply(WeatherList weatherList) {
                if (weatherList != null) {
                    adapter.setWeatherList(weatherList.getWeatherList());
                }
                progressBar.setVisibility(View.INVISIBLE);
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
