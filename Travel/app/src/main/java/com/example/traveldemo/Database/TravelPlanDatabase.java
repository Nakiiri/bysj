package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.TravelPlanDao;
import com.example.traveldemo.Entity.TravelPlan;

@Database(entities = {TravelPlan.class},version = 1,exportSchema = false)
public abstract class TravelPlanDatabase extends RoomDatabase {
    public static TravelPlanDatabase INSTANCE;
    public static synchronized TravelPlanDatabase getTravelPlanDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TravelPlanDatabase.class,"travelplan_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract TravelPlanDao getTravelPlanDao();
}
