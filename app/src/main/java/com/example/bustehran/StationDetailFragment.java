package com.example.bustehran;

import android.os.Bundle;
import android.util.Log; // اضافه کردن لاگ
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.bustehran.Webervice.GetJson;

import java.io.IOException;

public class StationDetailFragment extends Fragment {
    private GetJson getJson = new GetJson();
    private static final String ARG_STATION_ID = "stationId";
    private int stationId;

    public static StationDetailFragment newInstance(int stationId) {
        StationDetailFragment fragment = new StationDetailFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_detail, container, false);

        // Fetch data from the database or API
        fetchStationDetails();

        return view;
    }

    private void fetchStationDetails() {
        String url = "YOUR_API_URL_HERE"; // URL وب سرویس خود را وارد کنید

        try {
            String result = getJson.jsonRequest(url);
            // پردازش داده‌های JSON
        } catch (IOException e) {
            Log.e("StationDetailFragment", "Error fetching data: " + e.getMessage());
        }
    }
}
