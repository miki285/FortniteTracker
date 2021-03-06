package com.krzyszczak.fortnitetracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class StatisticFloat implements Serializable {

    @SerializedName("label") String label;
    @SerializedName("field") String field;
    @SerializedName("value") float value;

}
