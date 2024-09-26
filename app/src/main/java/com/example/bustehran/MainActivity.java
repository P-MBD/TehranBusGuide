package com.example.bustehran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
                Log.d(TAG, "Stations loaded: " + stations.size());
                adapter.setStations(stations);
            }
        });

        // رویداد کلیک برای هر ایستگاه
        adapter.setOnItemClickListener(station -> {
            Log.d(TAG, "Station clicked: " + station.id);
            Intent intent = new Intent(MainActivity.this, StationDetailActivity.class);
            intent.putExtra(EXTRA_STATION_ID, station.id);
            startActivity(intent);
        });
    }
}
