package com.krzyszczak.fortnitetracker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.krzyszczak.fortnitetracker.Platform;
import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.api.ApiAdapter;
import com.krzyszczak.fortnitetracker.api.YTApiAdapter;
import com.krzyszczak.fortnitetracker.models.PlayerInfo;
import com.krzyszczak.fortnitetracker.models.TrackerResponse;
import com.krzyszczak.fortnitetracker.models.YTResponse;
import com.krzyszczak.fortnitetracker.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.krzyszczak.fortnitetracker.Platform.PC;
import static com.krzyszczak.fortnitetracker.Platform.PS4;
import static com.krzyszczak.fortnitetracker.Platform.XBOX;

public class SearchActivity extends AppCompatActivity {

    private EditText searchAccountEditText;
    private ImageView pcImageView;
    private ImageView xboxImageView;
    private ImageView ps4ImageView;
    private Platform platform;
    private Button searchButton;
    private ProgressBar searchProgress;

    private float fullyVisibleAlpha;
    private float halfVisibleAlpha;

    private Disposable disposable;
    private Disposable YTdisposable;

    String playerNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        fullyVisibleAlpha = Utils.getFloatResource(this, R.dimen.image_fully_visible);
        halfVisibleAlpha = Utils.getFloatResource(this, R.dimen.image_half_visible);

        findViews();
        setPlatformImagesListeners();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForAccount();
            }
        });

        searchingForAccount(false);
        platform = PC;
    }

    @Override
    protected void onStop() {
        super.onStop();

        Utils.dispose(disposable);
    }

    private void findViews() {
        searchAccountEditText = findViewById(R.id.search_account_edittext);
        pcImageView = findViewById(R.id.pc_imageview);
        xboxImageView = findViewById(R.id.xbok_imageview);
        ps4ImageView = findViewById(R.id.ps4_imageview);
        searchButton = findViewById(R.id.search_button);
        searchProgress = findViewById(R.id.search_progress);
    }

    private void setPlatformImagesListeners() {
        pcImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcImageView.setAlpha(fullyVisibleAlpha);
                xboxImageView.setAlpha(halfVisibleAlpha);
                ps4ImageView.setAlpha(halfVisibleAlpha);
                platform = PC;
            }
        });

        xboxImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcImageView.setAlpha(halfVisibleAlpha);
                xboxImageView.setAlpha(fullyVisibleAlpha);
                ps4ImageView.setAlpha(halfVisibleAlpha);
                platform = XBOX;
            }
        });

        ps4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcImageView.setAlpha(halfVisibleAlpha);
                xboxImageView.setAlpha(halfVisibleAlpha);
                ps4ImageView.setAlpha(fullyVisibleAlpha);
                platform = PS4;
            }
        });
    }

    private void searchForAccount() {
        playerNick = searchAccountEditText.getText().toString();

        YTdisposable = ApiAdapter.getInstance().getPlayerStats(platform, playerNick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception { searchingForAccount(true);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        searchingForAccount(false);
                    }
                })
                .subscribe(new Consumer<TrackerResponse>() {
                    @Override
                    public void accept(TrackerResponse playerInfo) throws Exception {
                        searchForYT(playerInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Utils.logError("error occured : " + throwable.getMessage());
                    }
                });
    }



    private void searchForYT(final TrackerResponse playerInfo) {
        YTdisposable = YTApiAdapter.getInstance().getVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception { searchingForAccount(true);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        searchingForAccount(false);
                    }
                })
                .subscribe(new Consumer<YTResponse>() {
                    @Override
                    public void accept(YTResponse videoInfo) throws Exception {
                        startActivity(MainActivity.createIntent(SearchActivity.this,
                                platform, playerNick, playerInfo, videoInfo));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Utils.logError("error occured : " + throwable.getMessage());
                    }
                });
    }

    private void searchingForAccount(boolean isSearching) {
        if (isSearching) {
            searchProgress.setVisibility(VISIBLE);
            searchButton.setVisibility(GONE);
        } else {
            searchProgress.setVisibility(GONE);
            searchButton.setVisibility(VISIBLE);
        }
    }
}
