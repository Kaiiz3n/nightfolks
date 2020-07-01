package com.kaizen.nightfolks.controller.music.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.kaizen.nightfolks.R;
import com.kaizen.nightfolks.model.entities.Song;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public MaterialButton voteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.song_item_title);
            voteBtn = itemView.findViewById(R.id.voteBtn);

        }
    }


    private List<Song> mSongs;
    private int voteVisibility;

    public SongsAdapter(List<Song> songs, int voteVisibility) {
        this.voteVisibility = voteVisibility;
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
        holder.voteBtn.setVisibility(this.voteVisibility);
        holder.voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.voteBtn.setEnabled(false);
//                holder.voteBtn.setClickable(false);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mSongs.size();
    }
}
