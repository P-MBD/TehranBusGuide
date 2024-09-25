package com.example.bustehran;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StationDao {

    // واکشی ایستگاه با استفاده از stationId
    @Query("SELECT * FROM Tbl_Stations WHERE id = :stationId")
    LiveData<Station> getStationById(int stationId);

    // واکشی همه ایستگاه‌ها
    @Query("SELECT * FROM Tbl_Stations")
    LiveData<List<Station>> getAllStations();
}
