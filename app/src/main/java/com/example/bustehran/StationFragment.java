package com.example.bustehran;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class StationFragment extends Fragment {
    private StationViewModel stationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        StationAdapter adapter = new StationAdapter();
        recyclerView.setAdapter(adapter);

        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);
        stationViewModel.getAllStations().observe(getViewLifecycleOwner(), new Observer<List<Station>>() {
            @Override
            public void onChanged(List<Station> stations) {
                adapter.setStations(stations);
            }
        });

        return view;
    }
}
