package com.kaizen.nightfolks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;


public class Song implements Parcelable {
    private String mTitle;
    private String mPath;
    private int mVotes;

    public Song(String mTitle) {
        this.mTitle = mTitle;
        //this.mPath = mPath;
        this.mVotes = 0;
    }


    protected Song(Parcel in) {
        mTitle = in.readString();
        mPath = in.readString();
        mVotes = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPath);
        dest.writeInt(mVotes);
    }
}
