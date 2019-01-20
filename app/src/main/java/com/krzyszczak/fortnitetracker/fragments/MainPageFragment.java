package com.krzyszczak.fortnitetracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.models.PlayerInfo;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;

public class MainPageFragment extends Fragment {

    public static String TAG = "MainPageFragmentTag";
    public static String PLAYER_INFO_EXTRA = "PLAYER_INFO_EXTRA";
    private TrackerResponse playerInfo;

    private TextView mSquadsStatsTextView;

    public static MainPageFragment newInstance(TrackerResponse playerInfo) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER_INFO_EXTRA, playerInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerInfo = (TrackerResponse) getArguments().getSerializable(PLAYER_INFO_EXTRA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSquadsStatsTextView = (TextView) view.findViewById(R.id.stats_squads);
        mSquadsStatsTextView.append("test czy działa wpizdu");
        prepareStats();
        // todo dorobic ui
    }

    private String prepareStats(){
        return playerInfo.getStats().toString();
    }
}
