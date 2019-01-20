package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class PlayerInfo implements Serializable {

    @SerializedName("curr_p2") PlayerStats soloStats;
    @SerializedName("curr_p10") PlayerStats duoStats;
    @SerializedName("curr_p9") PlayerStats squadStats;
}
