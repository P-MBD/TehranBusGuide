package com.example.bustehran;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class StationPagerAdapter extends FragmentPagerAdapter {

    private Station station; // اضافه کردن Station

    public StationPagerAdapter(FragmentManager fm, Station station) {
        super(fm);
        this.station = station;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // ارسال اطلاعات ایستگاه به Fragment ها
                return StationDetailFragment.newInstance(station.getId()); // فرگمنت اطلاعات ایستگاه
            case 1:
                return new StationInfoFragment(); // فرگمنت نمایش جزئیات اضافی
            case 2:
                return new StationMapFragment(); // فرگمنت نقشه
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // تعداد فرگمنت‌هایی که در ViewPager دارید
    }
}
