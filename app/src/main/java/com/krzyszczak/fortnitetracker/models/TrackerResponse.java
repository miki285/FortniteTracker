package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrackerResponse implements Serializable {
    @SerializedName("stats") PlayerInfo soloStats;
}
