package com.kaizen.nightfolks.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kaizen.nightfolks.model.entities.Party;

import java.util.List;

@Dao
public interface PartyDao {
    @Query("Select * from party")
    List<Party> getPartyList();

    @Query("SELECT * FROM party WHERE host = :hostId")
    Party getPartyByHost(String hostId);

    @Insert
    void insertParty(Party party);


    @Delete
    void deleteParty(Party party);

    @Query("SELECT COUNT(*) FROM party")
    int getPartyCount();
}
