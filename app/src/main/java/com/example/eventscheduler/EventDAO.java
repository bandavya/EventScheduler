package com.example.eventscheduler;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDAO {

    @Insert
    void insertAll(EventClass EC);

    @Query("SELECT * FROM EventTable")
    List<EventClass> getAllData();
}
