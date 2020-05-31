package com.kaizen.nightfolks.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.kaizen.nightfolks.model.converters.DateConverter;

import java.sql.Date;
import java.util.Calendar;

@Entity(tableName = "party")
@TypeConverters(DateConverter.class)
public class Party {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "date")
    private Date date;
    @ColumnInfo(name = "host")
    private String host;

    //TODO:Suggestion
//    @ColumnInfo(name = "maximal_participants")
//    private int maxParticipants;

    public Party(String name, String genre, String host) {
        this.name = name;
        this.genre = genre;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.host = host;
    }


    @Ignore
    public Party(String name, String host) {
        this.name = name;
        this.host = host;
    }

    @Ignore
    public Party(Date date, String host) {
        this.date = date;
        this.host = host;
    }

    @Ignore
    public Party(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Party(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    @Ignore
    public Party(String genre, Date date) {
        this.genre = genre;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Date getDate() {
        return date;
    }

    public String getHost() {
        return host;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
//        Will maybe have to change precision later
//        boolean dateEquality = Math.abs(date.getTime() - party.date.getTime()) < 1000;
        return id == party.id &&
                name.equals(party.name) &&
                genre.equals(party.genre) &&
                date.equals(party.date) &&
                host.equals(party.host);
    }

}
