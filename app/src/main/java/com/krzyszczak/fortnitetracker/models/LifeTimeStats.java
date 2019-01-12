package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class LifeTimeStats implements Serializable {

    @SerializedName("key") String key;
    @SerializedName("value") int value;

}
