package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.UserDao;
import com.example.traveldemo.Entity.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE;
    public static synchronized UserDatabase getUserDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database")
                    .build();
        }
        return  INSTANCE;
    }
    public abstract UserDao getUserDao();
}
