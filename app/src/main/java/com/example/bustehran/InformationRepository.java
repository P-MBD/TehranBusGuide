package com.example.bustehran;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class InformationRepository {
    private InformationDao informationDao;

    public InformationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        informationDao = db.informationDao();
    }

    public LiveData<List<Information>> getInformationByStationId(int stationId) {
        return informationDao.getInformationByStationId(stationId);
    }
}
