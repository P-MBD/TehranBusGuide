package com.example.bustehran;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StationsListAdapter extends RecyclerView.Adapter<StationsListAdapter.StationViewHolder> {

    private List<Station> stations;
    private LayoutInflater inflater;

    public StationsListAdapter(Context context, List<Station> stations) {
        this.stations = stations;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.stations_row, parent, false);
        return new StationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {

        Station station = stations.get(position);

        // لاگ کردن اطلاعات هر آیتم در آداپتور
        Log.d("StationsListAdapter", "Binding station at position " + position + ": ID=" + station.getId() + ", Title=" + station.getTitle());

        holder.title.setText(station.getTitle());
        holder.address.setText(station.getAddress());
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
        notifyDataSetChanged(); // Update RecyclerView when data changes
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView address;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.station_title);
            address = itemView.findViewById(R.id.station_address);
        }
    }
}
