package com.kaizen.nightfolks.controller.music.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaizen.nightfolks.R;
import com.kaizen.nightfolks.controller.music.Song;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ToggleButton likeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.song_item_title);
            likeBtn = itemView.findViewById(R.id.song_item_likeBtn);
        }
    }

    private List<Song> mSongs;

    public SongsAdapter(List<Song> songs) {
        mSongs = songs;
    }

    @NonNull
    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.song_item, parent, false);
        return new ViewHolder(songView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapter.ViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.titleTextView.setText(song.getTitle());
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }
}
