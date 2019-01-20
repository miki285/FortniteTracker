package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class VideoSnippet implements Serializable {
    @SerializedName("thumbnails")
    VideoThumbnails videoThumbnails;

    @SerializedName("title")
    String title;
}
