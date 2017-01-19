package com.github.aprofromindia.weatherapp.http;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestClient {
    public static final String JSON_FORMAT = "json";
    public static final String WEATHER_YQL = "select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text in (\"tokyo\", \"new york\", \"sao paulo\", \"seoul\", \"mumbai\", \"delhi\", \"jakarta\", \"cairo\", \"los angeles\", \"buenos aires\", \"moscow\", \"shanghai\", \"paris\", \"istanbul\", \"beijing\", \"london\", \"singapore\", \"hong kong\", \"sydney\", \"madrid\", \"rio\"))";
    private static final String URL = "https://query.yahooapis.com/";
    private static final RestClient ourInstance = new RestClient();

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private Retrofit.Builder retroBuilder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(JacksonConverterFactory.create());
    private File cacheDir;

    private RestClient() {
    }

    public static RestClient getInstance(File cacheDir) {
        ourInstance.cacheDir = cacheDir;
        return ourInstance;
    }

    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = retroBuilder.client(httpClient
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(new Cache(cacheDir, 10 * 1024))
                .build()).build();
        return retrofit.create(serviceClass);
    }
}
