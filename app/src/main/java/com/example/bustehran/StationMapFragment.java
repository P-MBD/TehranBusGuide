package com.example.bustehran;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.bustehran.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StationMapFragment extends Fragment {
    private ListView lstData;
    private ProgressBar progressBar;
    private static final String ARG_STATION_ID = "station_id";
    private int stationId;
    private static final String TAG = "StationMapFragment";
    private ApiClient apiClient;

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

        // ایجاد شی از ApiClient
        apiClient = new ApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_station_map, container, false);
        lstData = rootView.findViewById(R.id.lst_data);
        progressBar = rootView.findViewById(R.id.progress);

        Log.d(TAG, "Fetching station data...");

        // آغاز واکشی ایستگاه‌ها
        fetchStations();

        return rootView;
    }

    private void fetchStations() {
        // نمایش ProgressBar قبل از آغاز واکشی
        progressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "Progress bar shown.");

        apiClient.fetchStations(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // مدیریت خطا
                Log.e(TAG, "Error fetching data: " + e.getMessage());
                e.printStackTrace();
                // پنهان کردن ProgressBar در صورت بروز خطا
                getActivity().runOnUiThread(() -> progressBar.setVisibility(View.INVISIBLE));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.d(TAG, "Response received: " + result);

                    List<Station> stationsList = parseJson(result);

                    // بروزرسانی UI در ترد اصلی
                    getActivity().runOnUiThread(() -> {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "Progress bar hidden.");

                        if (stationsList != null && !stationsList.isEmpty()) {
                            StationsListAdapter adapter = new StationsListAdapter(getActivity(), R.layout.stations_row, new ArrayList<>(stationsList));
                            lstData.setAdapter(adapter);
                            Log.d(TAG, "Stations list updated with " + stationsList.size() + " items.");
                        } else {
                            Log.d(TAG, "No stations found.");
                        }
                    });
                } else {
                    Log.e(TAG, "Server returned an error: " + response.code());
                    getActivity().runOnUiThread(() -> progressBar.setVisibility(View.INVISIBLE));
                }
            }
        });
    }

    private List<Station> parseJson(String jsonResponse) {
        List<Station> stationsList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Station station = new Station();
                station.setEnglishTitle(jsonObject.getString("English_title"));
                station.setTitle(jsonObject.getString("Title"));
                station.setLine(Integer.parseInt(jsonObject.getString("Line")));
                station.setAddress(jsonObject.getString("Address"));
                station.setLat(jsonObject.getString("Lat"));
                station.setLang(jsonObject.getString("Lang"));
                station.setDescription(jsonObject.getString("Description"));

                stationsList.add(station);
                Log.d(TAG, "Station added: " + station.getTitle());
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
        return stationsList;
    }
}
