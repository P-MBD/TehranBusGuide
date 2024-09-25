package com.example.bustehran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // اضافه کردن لاگ
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // ثابت برای لاگ
    // تعریف ثابت برای کلید station_id
    public static final String EXTRA_STATION_ID = "station_id";
    private StationViewModel stationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        StationAdapter adapter = new StationAdapter();
        recyclerView.setAdapter(adapter);

        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);
        stationViewModel.getAllStations().observe(this, new Observer<List<Station>>() {
            @Override
            public void onChanged(List<Station> stations) {
                Log.d(TAG, "Stations loaded: " + stations.size()); // اضافه کردن لاگ
                adapter.setStations(stations);
            }
        });

        // افزودن رویه کلیک به Adapter
        adapter.setOnItemClickListener(station -> {
            Log.d(TAG, "Station clicked: " + station.id); // لاگ کلیک آیتم
            Intent intent = new Intent(MainActivity.this, StationDetailActivity.class);
            intent.putExtra("stationId", station.id); // بررسی کنید که station.id
            startActivity(intent);
        });
    }
}
