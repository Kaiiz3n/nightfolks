package com.kaizen.nightfolks.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kaizen.nightfolks.model.entities.Party;

@Database(entities = Party.class, version = 1)
public abstract class PartyDatabase extends RoomDatabase {
    private static final String DB_NAME = "party_db";
    private static PartyDatabase instance;

    public static synchronized PartyDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PartyDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract PartyDao getPartyDao();
}

