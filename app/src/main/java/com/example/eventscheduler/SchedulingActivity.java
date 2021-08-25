package com.example.eventscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Database;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SchedulingActivity extends AppCompatActivity{
    Button time_button, date_button;
    EditText event_title;
    TextView timeTV, dateTV;
    EventDatabase event_DB;
    String alarm_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        event_title = findViewById(R.id.event_ET);
        timeTV = findViewById(R.id.time_displayer);
        dateTV = findViewById(R.id.date_displayer);

        time_button = (Button) findViewById(R.id.time_selector);
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(SchedulingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        alarm_time = i + ":" + i1;
                        timeTV.setText(FormatTime(i, i1));
                    }
                }, hour, minute, false);
                timePickerDialog.show();


            }
        });
        date_button = (Button) findViewById(R.id.date_selector);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SchedulingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateTV.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Button save = findViewById(R.id.done);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = event_title.getText().toString().trim();
                String date = dateTV.getText().toString();
                String time = timeTV.getText().toString();
                if(title.isEmpty()) {
                    Toast.makeText(SchedulingActivity.this, "Please enter the Event name", Toast.LENGTH_SHORT).show();
                }else{
                    if(time.equals("00:00") || date.equals("00-00-00")) {
                        Toast.makeText(SchedulingActivity.this, "Please select date and time", Toast.LENGTH_SHORT).show();
                    }else {
                        EventClass EC = new EventClass();
                        EC.setE_Date(date);
                        EC.setE_Name(title);
                        EC.setE_Time(time);
                        event_DB.EventDao().insertAll(EC);
                        setAlarm(title, date, time);
                        finish();

                    }
                }

            }
        });
        event_DB = EventDatabase.getDatabase(getApplicationContext());

    }


    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }



    private void setAlarm(String title, String date, String time){
        AlarmManager AM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(),EventBroadcast.class);


        intent.putExtra("event", title);
        intent.putExtra("time", time);
        intent.putExtra("date", date);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + alarm_time;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");

        //AM.setRepeating(AlarmManager.RTC_WAKEUP, al_time, 10000, pendingIntent);

        try {
            Date date1 = formatter.parse(dateandtime);
            //AM.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            AM.setRepeating(AlarmManager.RTC_WAKEUP, date1.getTime(), 10000, pendingIntent);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();


    }


}