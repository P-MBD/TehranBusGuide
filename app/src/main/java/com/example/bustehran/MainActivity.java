package com.example.bustehran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
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
                adapter.setStations(stations);
            }
        });
    }
}
