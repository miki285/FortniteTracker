package com.krzyszczak.fortnitetracker.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krzyszczak.fortnitetracker.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteFragment extends Fragment {

    public static String TAG = "InviteFragmentTag";

    public InviteFragment() {
        // Required empty public constructor
    }

    public static InviteFragment newInstance() {
        return new InviteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite, container, false);
    }

}
