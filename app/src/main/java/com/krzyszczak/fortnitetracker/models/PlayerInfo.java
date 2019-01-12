package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    @SerializedName("p2") PlayerStats soloStats;
    @SerializedName("p10") PlayerStats duoStats;
    @SerializedName("p9") PlayerStats squadStats;
}
