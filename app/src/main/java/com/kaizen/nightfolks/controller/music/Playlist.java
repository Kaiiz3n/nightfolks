package com.kaizen.nightfolks.controller.music;

import java.util.List;

public class Playlist {
    private List<Song> mSongs;


    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> mSongs) {
        this.mSongs = mSongs;
    }
}
