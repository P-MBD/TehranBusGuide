package com.example.bustehran;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class StationDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StationPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        // گرفتن stationId از Intent
        int stationId = getIntent().getIntExtra(MainActivity.EXTRA_STATION_ID, -1);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager_station_detail);

        myPagerAdapter = new StationPagerAdapter(getSupportFragmentManager(), stationId);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
