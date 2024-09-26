package com.example.bustehran;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StationInfoFragment extends Fragment {
    private TextView textViewTitle;
    private TextView textViewDescription;
    private StationViewModel stationViewModel;
    private static final String ARG_STATION_ID = "stationId";
    private int stationId;

    public static StationInfoFragment newInstance(int stationId) {
        StationInfoFragment fragment = new StationInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_station_info, container, false);

        // پیدا کردن TextView ها از فایل layout
        textViewTitle = view.findViewById(R.id.text_view_station_info_title);
        textViewDescription = view.findViewById(R.id.text_view_station_info_description);

        // گرفتن stationId از arguments
        if (getArguments() != null) {
            stationId = getArguments().getInt("stationId", -1);
        }

        // دریافت ViewModel
        stationViewModel = new ViewModelProvider(requireActivity()).get(StationViewModel.class);

        // واکشی اطلاعات ایستگاه با استفاده از stationId
        stationViewModel.getStationById(stationId).observe(getViewLifecycleOwner(), new Observer<Station>() {
            @Override
            public void onChanged(Station station) {
                // نمایش اطلاعات در TextView ها
                if (station != null) {
                    textViewTitle.setText(station.getTitle());
                    textViewDescription.setText(station.getDescription());
                }
            }
        });

        return view;
    }
}