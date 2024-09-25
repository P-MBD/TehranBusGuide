package com.example.bustehran;

import android.os.Bundle;
import android.util.Log; // اضافه کردن لاگ
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

public class StationDetailActivity extends AppCompatActivity {

    private static final String TAG = "StationDetailActivity"; // ثابت برای لاگ
    private ViewPager viewPager;
    private StationPagerAdapter stationPagerAdapter;
    private StationViewModel stationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        // تعریف ViewPager
        viewPager = findViewById(R.id.viewPager_station_detail);

        // دریافت stationId از Intent
        int stationId = getIntent().getIntExtra("station_id", -1);
        Log.d(TAG, "Received stationId: " + stationId); // لاگ برای بررسی دریافت stationId

        if (stationId == -1) {
            Log.e(TAG, "Invalid stationId received"); // لاگ در صورت مشکل در stationId
            return;
        }

        // تنظیم ViewModel
        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);

        // مشاهده تغییرات ایستگاه در LiveData
        stationViewModel.getStationById(stationId).observe(this, new Observer<Station>() {
            @Override
            public void onChanged(Station station) {

                // دریافت stationId با استفاده از کلید مشترک
                int stationId = getIntent().getIntExtra(MainActivity.EXTRA_STATION_ID, -1);

                if (station != null) {
                    Log.d(TAG, "Station loaded: " + station.getId() + " - " + station.getTitle()); // لاگ دریافت ایستگاه
                    // تنظیم Adapter برای ViewPager پس از دریافت داده‌ها
                    stationPagerAdapter = new StationPagerAdapter(getSupportFragmentManager(), station);
                    viewPager.setAdapter(stationPagerAdapter);
                } else {
                    Log.d(TAG, "Received stationId: " + stationId);//لاگ در صورت عدم یافتن ایستگاه
                }
            }
        });
    }
}
