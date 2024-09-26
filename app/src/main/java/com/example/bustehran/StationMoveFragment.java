package com.example.bustehran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StationMoveFragment extends Fragment {
    private TextView dayTextView;
    private TextView timeTextView;
    private int stationId;

    public StationMoveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // گرفتن id از Bundle
            stationId = getArguments().getInt("station_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_station_move, container, false);
        dayTextView = view.findViewById(R.id.text_view_day);
        timeTextView = view.findViewById(R.id.text_view_time);

        // نمایش day و time بر اساس stationId
        loadDataForStation(stationId);

        return view;
    }

    private void loadDataForStation(int id) {
        // بر اساس id اطلاعات day و time را از دیتابیس یا هر منبع دیگری بگیرید
        String day = "Saturday"; // مثال
        String time = "10:00 AM"; // مثال

        dayTextView.setText(day);
        timeTextView.setText(time);
    }
}
