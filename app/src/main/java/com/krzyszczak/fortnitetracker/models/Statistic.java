package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class Statistic implements Serializable {

    @SerializedName("label") String label;
    @SerializedName("field") String field;
    @SerializedName("value") int value;

}
