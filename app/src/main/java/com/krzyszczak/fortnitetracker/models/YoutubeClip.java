package com.krzyszczak.fortnitetracker.models;

import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.models.youtube.YTResponse;
import com.krzyszczak.fortnitetracker.models.youtube.YTVideos;
import com.krzyszczak.fortnitetracker.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.DrawableRes;

public class YoutubeClip {

    private final String url;
    private final String title;
    private final String videoId;


//
//    CLIP1(R.drawable.test,
//            "first clip",
//            "uy0f6fA10FM"),
//    CLIP3(R.drawable.test,
//            "first clip",
//            "uy0f6fA10FM"),
//    CLIP4(R.drawable.test,
//            "first clip",
//            "uy0f6fA10FM"),
//    CLIP2(R.drawable.ic_launcher_foreground,
//            "second clip",
//            "9xeB08BsrYY");

    public static List<YoutubeClip> getAllClips(YTResponse ytResponse) {
        if(ytResponse.getVideosList() == null || ytResponse.getVideosList().isEmpty()){
            Utils.logError("error occured : no YT videos");
        }
        List<YoutubeClip> clips = new ArrayList<>();
        for(YTVideos video : ytResponse.getVideosList()){
            String url = video.getVideoSnippet().getVideoThumbnails().getThumbDetails().getUrl();
            String id = video.getVideoId().getVideoId();
            String title = video.getVideoSnippet().getTitle();
            YoutubeClip clip = new YoutubeClip(url, title, id);
            clips.add(clip);
        }
        return clips;
    }


    YoutubeClip(String url, String title, String videoId) {
        this.url = url;
        this.title = title;
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoId() {
        return videoId;
    }
}
