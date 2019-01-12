package com.krzyszczak.fortnitetracker.models;

import com.krzyszczak.fortnitetracker.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.DrawableRes;

public enum YoutubeClip {

    CLIP1(R.drawable.test,
            "first clip",
            "uy0f6fA10FM"),
    CLIP3(R.drawable.test,
            "first clip",
            "uy0f6fA10FM"),
    CLIP4(R.drawable.test,
            "first clip",
            "uy0f6fA10FM"),
    CLIP2(R.drawable.ic_launcher_foreground,
            "second clip",
            "9xeB08BsrYY");

    public static List<YoutubeClip> getAllClips() {
        return Arrays.asList(CLIP1, CLIP2, CLIP3, CLIP4);
    }

    private final @DrawableRes int frameDrawable;
    private final String title;
    private final String videoId;

    YoutubeClip(@DrawableRes int frameDrawable, String title, String videoId) {
        this.frameDrawable = frameDrawable;
        this.title = title;
        this.videoId = videoId;
    }

    public int getFrameDrawable() {
        return frameDrawable;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoId() {
        return videoId;
    }
}
