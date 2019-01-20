package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class TrackerResponse implements Serializable {
    @SerializedName("stats") PlayerInfo stats;
}
