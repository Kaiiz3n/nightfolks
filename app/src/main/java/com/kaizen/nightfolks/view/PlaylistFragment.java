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

import com.kaizen.nightfolks.controller.music.adapters.SongsAdapter;
import com.kaizen.nightfolks.databinding.PlaylistFragmentBinding;
import com.kaizen.nightfolks.model.entities.Playlist;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PlaylistFragment extends Fragment {
    public static final String PARTYGURU = "guru";
    private Playlist songs;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PlaylistFragmentBinding playListFragmentBinding = PlaylistFragmentBinding
                .inflate(inflater, container, false);
        RecyclerView rvSongs = playListFragmentBinding.rvSongs;
        //RecyclerView Configuration
        rvSongs.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDivider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        rvSongs.addItemDecoration(itemDivider);
        songs = Playlist.createSongsList();
        String caller = Objects.requireNonNull(getActivity()).getIntent().getExtras().getString("caller");
        int voteVisibility = GONE;
        if (StringUtils.equals(caller, PARTYGURU)) {
            voteVisibility = VISIBLE;
        }
        SongsAdapter songsAdapter = new SongsAdapter(songs.getSongs(), voteVisibility);//Placeholder
        rvSongs.setAdapter(songsAdapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(getActivity()));
        return playListFragmentBinding.getRoot();
    }
}
