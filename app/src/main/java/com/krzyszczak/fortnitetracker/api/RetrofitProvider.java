package com.krzyszczak.fortnitetracker.api;

import retrofit2.Retrofit;

public class RetrofitProvider {

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if(instance == null) {
            instance = createRetrofit();
        }

        return instance;
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
    }

}
