package com.example.bustehran;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StationDao {
    @Query("SELECT * FROM Tbl_Stations")
    LiveData<List<Station>> getAllStations();
}
