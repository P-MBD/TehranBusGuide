package com.example.bustehran;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    private List<Station> stationList = new ArrayList<>();
    private OnItemClickListener listener;

    public StationAdapter() {
        // سازنده بدون آرگومان
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.textViewTitle.setText(station.getTitle());
        holder.textViewAddress.setText(station.getAddress());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(station);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void setStations(List<Station> stations) {
        this.stationList = stations;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewAddress;

        public StationViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Station station);
    }
}
