package com.example.bustehran;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class StationDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);

        viewPager = findViewById(R.id.viewPager);
        StationDetailPagerAdapter adapter = new StationDetailPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private class StationDetailPagerAdapter extends FragmentPagerAdapter {
        public StationDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new StationInfoFragment(); // اولین فرگمنت
                case 1:
                    return new StationScheduleFragment(); // فرگمنت دوم
                case 2:
                    return new StationMapFragment(); // فرگمنت سوم
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // تعداد فرگمنت‌ها
        }
    }
}
