package com.example.bustehran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StationTimesFragment extends Fragment {
    private RecyclerView recyclerViewStationTimes;
    private StationTimesAdapter adapter;
    private InformationViewModel informationViewModel;
    private static final String ARG_STATION_ID = "stationId";
    private int stationId;

    public static StationTimesFragment newInstance(int stationId) {
        StationTimesFragment fragment = new StationTimesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationId = getArguments().getInt(ARG_STATION_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_times, container, false);

        recyclerViewStationTimes = view.findViewById(R.id.recycler_view_station_times);
        recyclerViewStationTimes.setLayoutManager(new LinearLayoutManager(getContext()));

        informationViewModel = new ViewModelProvider(requireActivity()).get(InformationViewModel.class);

        // دریافت لیست اطلاعات بر اساس stationId
        informationViewModel.getInformationByStationId(stationId).observe(getViewLifecycleOwner(), new Observer<List<Information>>() {
            @Override
            public void onChanged(List<Information> informationList) {
                if (informationList != null) {
                    adapter = new StationTimesAdapter(informationList);
                    recyclerViewStationTimes.setAdapter(adapter);
                }
            }
        });

        return view;
    }
}
