package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.FavouriteDao;
import com.example.traveldemo.Entity.Favourite;

@Database(entities = {Favourite.class},version = 1,exportSchema = false)
public abstract class FavouriteDatabase extends RoomDatabase {
    private static FavouriteDatabase INSTANCE;
    public static synchronized FavouriteDatabase getFavouriteDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FavouriteDatabase.class,"favourite_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract FavouriteDao getFavouriteDao();
}
