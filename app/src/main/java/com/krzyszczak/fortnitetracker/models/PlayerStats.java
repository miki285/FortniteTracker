package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class PlayerStats implements Serializable {

    @SerializedName("top1") Statistic wins;
    @SerializedName("kd") StatisticFloat kd;
    @SerializedName("winRatio") StatisticFloat winRatio;
    @SerializedName("kills") Statistic kills;
    @SerializedName("matches") Statistic matches;

}
