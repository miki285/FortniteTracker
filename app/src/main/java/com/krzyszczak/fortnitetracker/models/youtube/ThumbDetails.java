package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ThumbDetails implements Serializable {

    @SerializedName("url")
    String url;
}
