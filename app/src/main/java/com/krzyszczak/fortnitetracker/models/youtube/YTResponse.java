package com.krzyszczak.fortnitetracker.models.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class YTResponse implements Serializable {
    @SerializedName("items")
    List<YTVideos> videosList;
}
