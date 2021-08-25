package com.example.eventscheduler;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EventTable")
public class EventClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    String E_Name, E_Time, E_Date;

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        E_Name = e_Name;
    }

    public String getE_Time() {
        return E_Time;
    }

    public void setE_Time(String e_Time) {
        E_Time = e_Time;
    }

    public String getE_Date() {
        return E_Date;
    }

    public void setE_Date(String e_Date) {
        E_Date = e_Date;
    }

}
