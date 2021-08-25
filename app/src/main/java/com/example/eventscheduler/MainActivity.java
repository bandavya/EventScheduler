package com.example.eventscheduler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private FloatingActionButton add_event_button;
    RecyclerView list_events;
    EventAdapter eventAdapter;
    EventDatabase eventDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_events = findViewById(R.id.event_list);

        add_event_button =  findViewById(R.id.add);
        add_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SchedulingActivity.class);
                startActivity(intent);
            }
        });

        eventDatabase = EventDatabase.getDatabase(getApplicationContext());

    }

    @Override
   protected void onResume() {

        super.onResume();
        setAdapter();

    }

    private void setAdapter() {
        List<EventClass> eventClassList = eventDatabase.EventDao().getAllData();
        eventAdapter = new EventAdapter(getApplicationContext(), eventClassList);
        list_events.setAdapter(eventAdapter);
    }


}