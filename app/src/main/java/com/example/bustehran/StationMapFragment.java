package com.example.bustehran;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationMapFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private StationsListAdapter adapter;
    private static final String TAG = "StationMapFragment";
    private static final String ARG_STATION_ID = "stationId";

    public static StationMapFragment newInstance(int stationId) {
        StationMapFragment fragment = new StationMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int stationId = getArguments().getInt(ARG_STATION_ID, -1);
            Log.d(TAG, "Station ID received: " + stationId);
        } else {
            Log.d(TAG, "No Station ID received");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView started");
        View rootView = inflater.inflate(R.layout.fragment_station_map, container, false);

        // مقداردهی RecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_view);
        progressBar = rootView.findViewById(R.id.progress);

        // تنظیم LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // تنظیم آداپتور با لیست خالی
        adapter = new StationsListAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // تنظیم LayoutManager برای RecyclerView


        Log.d(TAG, "Fetching station data...");
        fetchStations();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Fragment resumed");
    }

    private void fetchStations() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Station>> call = apiService.getStations();
        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Station> stationsList = response.body();
                    Log.d(TAG, "API Response: " + new Gson().toJson(response.body()));
                    // لاگ کردن آیتم‌ها برای اشکال‌زدایی
                    for (int i = 0; i < stationsList.size(); i++) {
                        Station station = stationsList.get(i);
                        Log.d(TAG, "Station " + i + ": ID=" + station.getId() + ", Title=" + station.getTitle() + ", Address=" + station.getAddress());
                    }

                    // تنظیم آداپتور
                    StationsListAdapter adapter = new StationsListAdapter(getActivity(), new ArrayList<>(stationsList));
                    recyclerView.setAdapter(adapter);

                    Log.d(TAG, "Stations list updated with " + stationsList.size() + " items.");
                    Log.d(TAG, "Adapter item count: " + adapter.getItemCount());
                } else {
                    Log.d(TAG, "No stations found or empty response.");
                }
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e(TAG, "Error fetching data: " + t.getMessage());
            }
        });
    }

}
