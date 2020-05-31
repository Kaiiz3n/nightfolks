package com.kaizen.nightfolks.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaizen.nightfolks.controller.music.Song;
import com.kaizen.nightfolks.controller.music.adapters.SongsAdapter;
import com.kaizen.nightfolks.databinding.DjPlaylistFragmentBinding;

import java.util.ArrayList;

public class DJPlaylistFragment extends Fragment {
    ArrayList<Song> songs;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DjPlaylistFragmentBinding djPlayListFragmentBinding = DjPlaylistFragmentBinding
                .inflate(inflater, container, false);
        RecyclerView rvSongs = djPlayListFragmentBinding.rvSongs;

        //RecyclerView Configuration
        rvSongs.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDivider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        rvSongs.addItemDecoration(itemDivider);
        songs = Song.createSongsList();
        SongsAdapter songsAdapter = new SongsAdapter(songs);
        rvSongs.setAdapter(songsAdapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(getActivity()));
        return djPlayListFragmentBinding.getRoot();
    }
}
