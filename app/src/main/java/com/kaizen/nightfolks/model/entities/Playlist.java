package com.kaizen.nightfolks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Parcelable {
    private List<Song> mSongs;

    public Playlist(List<Song> mSongs) {
        this.mSongs = mSongs;
    }

    protected Playlist(Parcel in) {
        mSongs = in.createTypedArrayList(Song.CREATOR);
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> mSongs) {
        this.mSongs = mSongs;
    }

    // Only as placeHolder method , to be overwritten/deleted when the real feature is implemented
    public static Playlist createSongsList() {
        ArrayList<Song> randomSongs = new ArrayList<Song>();
        randomSongs.add(new Song("NF - The Search"));
        randomSongs.add(new Song("EL KATIBA - BlackBox"));
        randomSongs.add(new Song("Klay Bbj - Ghodwa 5ir"));
        randomSongs.add(new Song("PNL - A l'Ammoniaque"));
        randomSongs.add(new Song("Dax - Book of Revelation"));
        randomSongs.add(new Song("Boulevard des Airs - Emmène-moi"));

        return new Playlist(randomSongs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(this.mSongs.toArray());
    }
}
