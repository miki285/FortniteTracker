package com.krzyszczak.fortnitetracker.api;

import com.krzyszczak.fortnitetracker.Platform;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;
import com.krzyszczak.fortnitetracker.models.YTResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YTApiAdapter {

    private static YTApiAdapter apiAdapter;

    private final YTApi api;

    public static YTApiAdapter getInstance() {
        if (apiAdapter == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(5, TimeUnit.SECONDS);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            YTApi api = retrofit.create(YTApi.class);
            apiAdapter = new YTApiAdapter(api);
        }

        return apiAdapter;
    }

    YTApiAdapter(YTApi api) {
        this.api = api;
    }

    public Single<YTResponse> getVideos() {
        return api.getYTVideos();
    }
}
