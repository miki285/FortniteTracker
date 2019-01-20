package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class VideoThumbnails implements Serializable {

    @SerializedName("high")
    ThumbDetails thumbDetails;
}
