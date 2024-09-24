package com.example.bustehran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StationViewModel extends AndroidViewModel {
    private StationRepository repository;
    private LiveData<List<Station>> allStations;

    public StationViewModel(@NonNull Application application) {
        super(application);
        repository = new StationRepository(application);
        allStations = repository.getAllStations();
    }

    public LiveData<List<Station>> getAllStations() {
        return allStations;
    }
}
