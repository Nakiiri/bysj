package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.MarkDao;
import com.example.traveldemo.Entity.Mark;

@Database(entities = {Mark.class},version = 1,exportSchema = false)
public abstract class MarkDatabase extends RoomDatabase {
    private static MarkDatabase INSTANCE;
    public static synchronized MarkDatabase getMarkDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MarkDatabase.class,"mark_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract MarkDao getMarkDao();
}
