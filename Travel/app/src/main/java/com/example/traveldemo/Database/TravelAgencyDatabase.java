package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.TravelAgencyDao;
import com.example.traveldemo.Entity.TravelAgency;

@Database(entities = {TravelAgency.class}, version = 1,exportSchema = false)
public abstract class TravelAgencyDatabase extends RoomDatabase {
    private static TravelAgencyDatabase INSTANCE;
    public static synchronized TravelAgencyDatabase getTravelAgencyDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TravelAgencyDatabase.class,"travelagency_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract TravelAgencyDao getTravelAgencyDao();
}
