package com.example.bustehran;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class StationMoveFragment extends Fragment {
    private static final String BASE_URL = "http://127.0.0.1:8000/api/stations/";
    private RecyclerView recyclerView;
    private StationAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_move, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewStations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // بارگذاری داده‌ها
        new FetchStationsTask().execute(BASE_URL);
        return view;
    }

    private class FetchStationsTask extends AsyncTask<String, Void, List<Station>> {
        @Override
        protected List<Station> doInBackground(String... urls) {
            String jsonResponse = "";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    jsonResponse = stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // تبدیل JSON به لیست ایستگاه‌ها
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, new TypeToken<List<Station>>(){}.getType());
        }

        @Override
        protected void onPostExecute(List<Station> stations) {
            if (stations != null) {
                if (adapter == null) {
                    adapter = new StationAdapter();
                    recyclerView.setAdapter(adapter);
                }
                adapter.setStations(stations); // لیست ایستگاه‌ها را تنظیم کنید
            }
        }
    }
}
