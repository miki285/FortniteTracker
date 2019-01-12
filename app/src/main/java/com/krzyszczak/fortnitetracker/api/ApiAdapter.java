package com.krzyszczak.fortnitetracker.api;

import com.krzyszczak.fortnitetracker.Platform;
import com.krzyszczak.fortnitetracker.models.PlayerInfo;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;

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

public class ApiAdapter {

    private static ApiAdapter apiAdapter;

    private final Api api;

    public static ApiAdapter getInstance() {
        if (apiAdapter == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(5, TimeUnit.SECONDS);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("TRN-Api-Key",
                            "3d974446-5ee8-4834-8b60-e65bffe87cf0").build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl("https://api.fortnitetracker.com/v1/profile/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);
            apiAdapter = new ApiAdapter(api);
        }

        return apiAdapter;
    }

    ApiAdapter(Api api) {
        this.api = api;
    }

    public Single<TrackerResponse> getPlayerStats(Platform platform, String playerNick) {
        return api.getPlayerStats(platform.getValue(), playerNick);
    }
}
