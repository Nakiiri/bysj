package com.example.traveldemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.traveldemo.Dao.OrderDao;
import com.example.traveldemo.Entity.Order;

@Database(entities = {Order.class},version = 1,exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {
    private static OrderDatabase INSTANCE;
    public static synchronized OrderDatabase getOrderDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),OrderDatabase.class,"order_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract OrderDao getOrderDao();
}
