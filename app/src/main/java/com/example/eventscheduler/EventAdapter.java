package com.example.eventscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EventClass> EC_List ;

    public EventAdapter(Context context, List<EventClass> EC_List ){
        this.context = context;
        this.EC_List = EC_List;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_eventname.setText(EC_List.get(position).getE_Name());
        holder.item_date.setText(EC_List.get(position).getE_Date());
        holder.item_time.setText(EC_List.get(position).getE_Time());

    }

    @Override
    public int getItemCount() {
        return EC_List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_eventname, item_time, item_date;
        private LinearLayout item_display;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_eventname = itemView.findViewById(R.id.item_eventname);
            item_date = itemView.findViewById(R.id.item_date);
            item_time = itemView.findViewById(R.id.item_time);
            item_display = itemView.findViewById(R.id.item_display_card);
        }
    }
}
