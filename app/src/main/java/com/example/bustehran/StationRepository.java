package com.example.bustehran;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StationRepository {
    private StationDao stationDao;
    private LiveData<List<Station>> allStations;

    public StationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        stationDao = db.stationDao();
        allStations = stationDao.getAllStations();
    }

    public LiveData<List<Station>> getAllStations() {
        return allStations;
    }
}
