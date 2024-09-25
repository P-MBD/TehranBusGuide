package com.example.bustehran;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface InformationDao {


    @Query("SELECT * FROM Tbl_Information WHERE Id_Station = :stationId")
    LiveData<List<Information>> getInformationByStationId(int stationId);
}