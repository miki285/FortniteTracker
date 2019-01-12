package com.krzyszczak.fortnitetracker.api;

import com.krzyszczak.fortnitetracker.models.PlayerInfo;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("{platform}/{epic-nickname}")
    Single<TrackerResponse> getPlayerStats(@Path("platform") String platform,
                                           @Path("epic-nickname") String playerNick);

}
