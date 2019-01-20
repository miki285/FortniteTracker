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
import com.krzyszczak.fortnitetracker.models.PlayerStats;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {

    public static String TAG = "MainPageFragmentTag";
    public static String PLAYER_INFO_EXTRA = "PLAYER_INFO_EXTRA";
    private TrackerResponse playerInfo;

    private TextView mSoloHeaderView;
    private TextView mDuosHeaderView;
    private TextView mSquadsHeaderView;

    private TextView mSoloBodyView;
    private TextView mDuosBodyView;
    private TextView mSquadsBodyView;

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

        mSoloHeaderView = (TextView) view.findViewById(R.id.solo_header);
        mDuosHeaderView = (TextView) view.findViewById(R.id.duos_header);
        mSquadsHeaderView = (TextView) view.findViewById(R.id.squads_header);

        mSoloBodyView = (TextView) view.findViewById(R.id.stats_solo);
        mDuosBodyView = (TextView) view.findViewById(R.id.stats_duos);
        mSquadsBodyView = (TextView) view.findViewById(R.id.stats_squads);


        List<String> solo = prepareStats("solo");
        List<String> duos = prepareStats("duos");
        List<String> squads = prepareStats("squads");

        mSoloHeaderView.append(solo.get(0));
        mSoloBodyView.append(solo.get(1));

        mDuosHeaderView.append(duos.get(0));
        mDuosBodyView.append(duos.get(1));

        mSquadsHeaderView.append(squads.get(0));
        mSquadsBodyView.append(squads.get(1));


    }

    private List<String> prepareStats(String mode) {

        List<String> res = new ArrayList<>();
        PlayerInfo stats = playerInfo.getStats();
        switch (mode) {
            case "solo": {
                if (stats == null || stats.getSoloStats() == null) {
                    res.add("- 0 games");
                    res.add("There are no stats for the Solo playlist.\n" +
                            "Play some matches first!");
                } else {
                    StringBuilder gamesBuilder = new StringBuilder();
                    gamesBuilder.append("- " + stats.getSoloStats().getMatches().getValue());
                    gamesBuilder.append(" games");
                    res.add(gamesBuilder.toString());

                    PlayerStats soloStats = stats.getSoloStats();
                    StringBuilder statsBuilder = new StringBuilder();
                    statsBuilder.append(soloStats.getKd().getLabel() + ": " + soloStats.getKd().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(soloStats.getKills().getLabel() + ": " + soloStats.getKills().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(soloStats.getKpg().getLabel() + ": " + soloStats.getKpg().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(soloStats.getWins().getLabel() + ": " + soloStats.getWins().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(soloStats.getWinRatio().getLabel() + ": " + soloStats.getWinRatio().getValue());

                    res.add(statsBuilder.toString());
                }
                break;
            }
            case "duos": {
                if (stats == null ||stats.getDuoStats() == null) {
                    res.add("- 0 games");
                    res.add("There are no stats for the Duos playlist.\n" +
                            "Play some matches first!");
                } else {
                    StringBuilder gamesBuilder = new StringBuilder();
                    gamesBuilder.append("- " + stats.getDuoStats().getMatches().getValue());
                    gamesBuilder.append(" games");
                    res.add(gamesBuilder.toString());

                    PlayerStats duosStats = stats.getDuoStats();
                    StringBuilder statsBuilder = new StringBuilder();
                    statsBuilder.append(duosStats.getKd().getLabel() + ": " + duosStats.getKd().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(duosStats.getKills().getLabel() + ": " + duosStats.getKills().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(duosStats.getKpg().getLabel() + ": " + duosStats.getKpg().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(duosStats.getWins().getLabel() + ": " + duosStats.getWins().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(duosStats.getWinRatio().getLabel() + ": " + duosStats.getWinRatio().getValue());

                    res.add(statsBuilder.toString());
                }
                break;
            }
            case "squads": {
                if (stats == null ||stats.getSquadStats() == null) {
                    res.add("- 0 games");
                    res.add("There are no stats for the Squads playlist.\n" +
                            "Play some matches first!");
                } else {
                    StringBuilder gamesBuilder = new StringBuilder();
                    gamesBuilder.append("- " + stats.getSquadStats().getMatches().getValue());
                    gamesBuilder.append(" games");
                    res.add(gamesBuilder.toString());

                    PlayerStats squadsStats = stats.getSquadStats();
                    StringBuilder statsBuilder = new StringBuilder();
                    statsBuilder.append(squadsStats.getKd().getLabel() + ": " + squadsStats.getKd().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(squadsStats.getKills().getLabel() + ": " + squadsStats.getKills().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(squadsStats.getKpg().getLabel() + ": " + squadsStats.getKpg().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(squadsStats.getWins().getLabel() + ": " + squadsStats.getWins().getValue());
                    statsBuilder.append("\n");
                    statsBuilder.append(squadsStats.getWinRatio().getLabel() + ": " + squadsStats.getWinRatio().getValue());

                    res.add(statsBuilder.toString());
                }
                break;
            }
        }
        return res;
    }
}
