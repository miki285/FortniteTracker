package com.krzyszczak.fortnitetracker.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krzyszczak.fortnitetracker.OnYoutubeClipClickedListener;
import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.adapters.YoutubeRecyclerAdapter;
import com.krzyszczak.fortnitetracker.models.YoutubeClip;

public class YoutubeFragment extends Fragment {

    public static String TAG = "YoutubeFragmentTag";

    public static YoutubeFragment newInstance() {
        return new YoutubeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new YoutubeRecyclerAdapter(YoutubeClip.getAllClips(),
                new OnYoutubeClipClickedListener() {
            @Override
            public void onClick(YoutubeClip youtubeClip) {
                startYoutube(youtubeClip.getVideoId());
            }
        }));
    }

    private void startYoutube(String videoId) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + videoId));
        try {
            getActivity().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            getParentFragment().startActivity(webIntent);
        }
    }
}
