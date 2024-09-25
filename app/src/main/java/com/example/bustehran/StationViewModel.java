package com.example.bustehran;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class StationViewModel extends AndroidViewModel {
    private StationRepository stationRepository;
    private MutableLiveData<Station> stationLiveData; // تعریف stationLiveData به عنوان MutableLiveData

    public StationViewModel(Application application) {
        super(application);
        stationRepository = new StationRepository(application);
        stationLiveData = new MutableLiveData<>(); // مقداردهی اولیه stationLiveData
    }



    // متد برای واکشی ایستگاه بر اساس ID
    public LiveData<Station> getStationById(int stationId) {
        return stationRepository.getStationById(stationId); // LiveData<Station> را بازگردانید
    }

    // متد برای واکشی همه ایستگاه‌ها
    public LiveData<List<Station>> getAllStations() {
        return stationRepository.getAllStations();
    }


}
