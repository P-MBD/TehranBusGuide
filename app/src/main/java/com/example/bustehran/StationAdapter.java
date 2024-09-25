package com.example.bustehran;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    private List<Station> stations = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new StationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station currentStation = stations.get(position);
        holder.textView.setText(currentStation.getTitle());

        // افزودن کلیک لیسنر برای هر آیتم
        holder.itemView.setOnClickListener(view -> {
            // گرفتن id ایستگاه فعلی و لاگ کردن آن
            Log.d("StationAdapter", "Clicked station id: " + currentStation.id);
            Intent intent = new Intent(view.getContext(), StationDetailActivity.class);
            // استفاده از ثابت تعریف شده در MainActivity
            intent.putExtra(MainActivity.EXTRA_STATION_ID, currentStation.id);// ارسال id ایستگاه به StationDetailActivity
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Station station);
    }

    class StationViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public StationViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
