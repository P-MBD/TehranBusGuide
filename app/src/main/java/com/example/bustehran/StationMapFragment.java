package com.example.bustehran;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;


import com.example.bustehran.Webervice.GetJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StationMapFragment extends Fragment {
    private ListView lstData;
    private ProgressBar progressBar;
    private static final String ARG_STATION_ID = "station_id";
    private int stationId;
    private static final String BASE_URL = "http://172.20.2.26:8000/api/stations/";

    // برای مشخص کردن تگ لاگ‌ها
    private static final String TAG = "StationMapFragment";

    public static StationMapFragment newInstance(int stationId) {
        StationMapFragment fragment = new StationMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stationId = getArguments().getInt(ARG_STATION_ID);
            Log.d(TAG, "Station ID: " + stationId); // لاگ برای بررسی مقدار Station ID
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_station_map, container, false);
        lstData = rootView.findViewById(R.id.lst_data);
        progressBar = rootView.findViewById(R.id.progress);

        Log.d(TAG, "Fetching station data..."); // لاگ برای آغاز واکشی داده‌ها

        // آغاز واکشی ایستگاه‌ها
        new FetchStationsTask().execute("http://172.20.2.26:8000/api/stations/");

        return rootView;
    }

    private class FetchStationsTask extends AsyncTask<String, Void, List<Station>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // نمایش ProgressBar قبل از آغاز واکشی
            progressBar.setVisibility(View.VISIBLE);
            Log.d(TAG, "Progress bar shown."); // لاگ برای نمایش ProgressBar
        }

        @Override
        protected List<Station> doInBackground(String... urls) {
            Log.d(TAG, "Requesting data from URL: " + urls[0]); // لاگ برای URL درخواست
            List<Station> stationsList = new ArrayList<>();
            try {
                GetJson getJson = new GetJson();
                String result = getJson.jsonRequest(urls[0]);
                Log.d(TAG, "Response received: " + result); // لاگ برای نتیجه JSON

                // پارس کردن JSON
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Station station = new Station();
                    station.setEnglishTitle(jsonObject.getString("English_title"));
                    station.setTitle(jsonObject.getString("Title"));
                    station.setLine(Integer.parseInt(jsonObject.getString("Line")));
                    station.setAddress(jsonObject.getString("Address"));
                    station.setLat(jsonObject.getString("Lat"));
                    station.setLang(jsonObject.getString("Lang")); // تغییر از Lane به Lang
                    station.setDescription(jsonObject.getString("Description"));

                    stationsList.add(station);
                    Log.d(TAG, "Station added: " + station.getTitle()); // لاگ برای هر ایستگاه اضافه شده
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON: " + e.getMessage()); // لاگ در صورت خطای JSON
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(TAG, "Error fetching data: " + e.getMessage()); // لاگ در صورت خطای دیگر
                e.printStackTrace();
            }
            return stationsList;
        }

        @Override
        protected void onPostExecute(List<Station> stationsList) {
            // پنهان کردن ProgressBar و به‌روزرسانی لیست
            progressBar.setVisibility(View.INVISIBLE);
            Log.d(TAG, "Progress bar hidden."); // لاگ برای پنهان شدن ProgressBar

            if (stationsList != null && !stationsList.isEmpty()) {
                StationsListAdapter adapter = new StationsListAdapter(getActivity(), R.layout.stations_row, new ArrayList<>(stationsList));
                lstData.setAdapter(adapter);
                Log.d(TAG, "Stations list updated with " + stationsList.size() + " items."); // لاگ برای به‌روزرسانی لیست
            } else {
                Log.d(TAG, "No stations found."); // لاگ در صورتی که لیستی پیدا نشود
            }
        }
    }
}
