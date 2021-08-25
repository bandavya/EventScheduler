package com.example.eventscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class EventNotificationActivity extends AppCompatActivity {

    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_notification);

        message = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        message.setText(bundle.getString("message"));


        Toast.makeText(EventNotificationActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();

    }
}