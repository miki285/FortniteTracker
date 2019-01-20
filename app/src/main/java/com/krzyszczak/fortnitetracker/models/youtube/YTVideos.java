package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class YTVideos implements Serializable {
    @SerializedName("id")
    VideoId videoId;

    @SerializedName("snippet")
    VideoSnippet videoSnippet;
}
