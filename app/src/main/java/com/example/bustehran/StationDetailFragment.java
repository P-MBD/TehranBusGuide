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

public class StationDetailFragment extends Fragment {

    private static final String TAG = "StationDetailFragment"; // ثابت برای لاگ
    private static final String ARG_STATION_ID = "station_id";
    private int stationId;

    public static StationDetailFragment newInstance(int stationId) {
        StationDetailFragment fragment = new StationDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationId = getArguments().getInt(ARG_STATION_ID);
            Log.d(TAG, "StationDetailFragment created with stationId: " + stationId); // لاگ دریافت stationId
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_detail, container, false);
        TextView textView = view.findViewById(R.id.textView_information);
        textView.setText("Station ID: " + stationId); // نمایش ID ایستگاه
        Log.d(TAG, "Station ID displayed: " + stationId); // لاگ نمایش stationId
        return view;
    }
}
