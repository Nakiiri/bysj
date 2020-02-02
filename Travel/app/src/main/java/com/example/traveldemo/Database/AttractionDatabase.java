package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.AttractionDao;
import com.example.traveldemo.Entity.Attraction;

@Database(entities = {Attraction.class},version = 1,exportSchema = false)
public abstract class AttractionDatabase extends RoomDatabase {
    private static AttractionDatabase INSTANCE;
    public static synchronized AttractionDatabase getAttractionDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AttractionDatabase.class,"attraction_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract AttractionDao getAttractionDao();
}
