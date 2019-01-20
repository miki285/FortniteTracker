package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class VideoId implements Serializable {
    @SerializedName("videoId")
    String videoId;
}
