package com.krzyszczak.fortnitetracker.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.krzyszczak.fortnitetracker.OnYoutubeClipClickedListener;
import com.krzyszczak.fortnitetracker.R;
import com.krzyszczak.fortnitetracker.models.YoutubeClip;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<YoutubeRecyclerAdapter.YoutubeViewHolder> {

    private final List<YoutubeClip> clips;
    private final OnYoutubeClipClickedListener onYoutubeClipClickedListener;

    public YoutubeRecyclerAdapter(List<YoutubeClip> clips,
                                  OnYoutubeClipClickedListener onYoutubeClipClickedListener) {
        this.clips = clips;
        this.onYoutubeClipClickedListener = onYoutubeClipClickedListener;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YoutubeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_youtube_clip, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder holder, int position) {
        final YoutubeClip youtubeClip = clips.get(position);

        Picasso.get().load(youtubeClip.getUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.itemView.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYoutubeClipClickedListener.onClick(youtubeClip);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clips.size();
    }

    public static class YoutubeViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleTextView;

        public YoutubeViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.youtube_clip_title);
        }
    }
}
