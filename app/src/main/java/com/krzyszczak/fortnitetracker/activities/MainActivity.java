package com.krzyszczak.fortnitetracker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.krzyszczak.fortnitetracker.Platform;
import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.fragments.InviteFragment;
import com.krzyszczak.fortnitetracker.fragments.MainPageFragment;
import com.krzyszczak.fortnitetracker.fragments.YoutubeFragment;
import com.krzyszczak.fortnitetracker.models.PlayerInfo;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private static final String PLATFORM_EXTRA = "PLATFORM_EXTRA";
    private static final String PLAYER_NICK_EXTRA = "PLAYER_NICK_EXTRA";
    private static final String PLAYER_INFO_EXTRA = "PLAYER_INFO_EXTRA";
    private BottomNavigationView bottomNavigation;

    public static Intent createIntent(Context context, Platform platform, String playerNick,
                                      TrackerResponse playerInfo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(PLATFORM_EXTRA, platform);
        intent.putExtra(PLAYER_NICK_EXTRA, playerNick);
        intent.putExtra(PLAYER_INFO_EXTRA, playerInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getIntent().getStringExtra(PLAYER_NICK_EXTRA));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.main_page: {
                        changeFragment(MainPageFragment.TAG, MainPageFragment.newInstance(
                                (TrackerResponse) getIntent().getSerializableExtra(PLAYER_INFO_EXTRA)));
                        break;
                    }
                    case R.id.youtube: {
                        changeFragment(YoutubeFragment.TAG, YoutubeFragment.newInstance());
                        break;
                    }
                    case R.id.invite: {
                        changeFragment(InviteFragment.TAG, InviteFragment.newInstance());
                        break;
                    }
                }
                return true;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.main_page);
    }

    private void changeFragment(String tag, Fragment fragment) {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentByTag != null) {
            fragment = fragmentByTag;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
