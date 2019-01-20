package com.krzyszczak.fortnitetracker.api;

import com.krzyszczak.fortnitetracker.models.TrackerResponse;
import com.krzyszczak.fortnitetracker.models.YTResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface YTApi {

    @GET("search?part=snippet&q=fortnite&key=AIzaSyBIHR2G01IDQKWV516P9_e7X5VH0CR6iQY&maxResults=15")
    Single<YTResponse> getYTVideos();

}
