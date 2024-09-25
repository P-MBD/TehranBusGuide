package com.example.bustehran;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InformationViewModel extends AndroidViewModel {
    private InformationRepository repository;
    private LiveData<List<Information>> informationByStationId;

    public InformationViewModel(@NonNull Application application) {
        super(application);
        repository = new InformationRepository(application);
    }

    public LiveData<List<Information>> getInformationByStationId(int stationId) {
        return repository.getInformationByStationId(stationId);
    }
}
