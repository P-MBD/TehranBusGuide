package com.example.bustehran;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StationRepository {
    private StationDao stationDao;
    private LiveData<List<Station>> allStations;

    // سازنده برای دریافت دیتابیس
    public StationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        stationDao = db.stationDao();
        allStations = stationDao.getAllStations();
    }

    // متد برای دریافت لیست همه ایستگاه‌ها
    public LiveData<List<Station>> getAllStations() {
        return allStations;
    }


    // متد برای واکشی ایستگاه بر اساس ID
    public LiveData<Station> getStationById(int stationId) {
        return stationDao.getStationById(stationId); // LiveData<Station> را بازگردانید
    }
}
