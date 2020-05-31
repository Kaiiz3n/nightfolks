package com.kaizen.nightfolks.controller.music;

import java.util.ArrayList;

public class Song {
    private String mTitle;
    private String mPath;
    private int mVotes;

    public Song(String mTitle) {
        this.mTitle = mTitle;
        //this.mPath = mPath;
        this.mVotes = 0;
    }

    // Only as placeHolder method , to be overwritten/deleted when the real feature is implemented
    public static ArrayList<Song> createSongsList() {
        ArrayList<Song> randomSongs = new ArrayList<Song>();
        randomSongs.add(new Song("KASO - Démission"));
        randomSongs.add(new Song("EL KATIBA - BlackBox"));
        randomSongs.add(new Song("PNL - A l'Ammoniaque"));
        randomSongs.add(new Song("Radi - Pray"));
        randomSongs.add(new Song("Radi - Pray"));

        randomSongs.add(new Song("Radi - Pray"));
        randomSongs.add(new Song("Radi - Pray"));
        randomSongs.add(new Song("Radi - Pray"));
        randomSongs.add(new Song("Radi - Pray"));

        return randomSongs;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public int getVotes() {
        return mVotes;
    }

    public void setVotes(int mVotes) {
        this.mVotes = mVotes;
    }
}
