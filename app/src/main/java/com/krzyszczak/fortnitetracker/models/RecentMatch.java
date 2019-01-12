package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

class RecentMatch implements Serializable {

    @SerializedName("kills") int kills;
    @SerializedName("dateCollected") Date dateCollected;
    @SerializedName("score") int score;
    @SerializedName("top1") int top1;
    @SerializedName("matches") int matches;

}
