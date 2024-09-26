package com.example.bustehran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StationTimesAdapter extends RecyclerView.Adapter<StationTimesAdapter.StationTimesViewHolder> {
    private List<Information> informationList;

    public StationTimesAdapter(List<Information> informationList) {
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public StationTimesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station_time, parent, false);
        return new StationTimesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationTimesViewHolder holder, int position) {
        Information information = informationList.get(position);
        holder.textViewDay.setText(information.getDay());
        holder.textViewDepartureTime.setText(information.getTime());
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    public static class StationTimesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDay;
        TextView textViewDepartureTime;

        public StationTimesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDay = itemView.findViewById(R.id.text_view_day);
            textViewDepartureTime = itemView.findViewById(R.id.text_view_departure_time);
        }
    }
}
