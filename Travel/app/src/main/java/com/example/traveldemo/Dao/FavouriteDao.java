package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Favourite;

import java.util.List;

@Dao
public interface FavouriteDao {
    @Insert
    void insertFavourite(Favourite...favourites);

    @Delete
    void deleteFavourite(Favourite...favourites);

    @Query("SELECT * FROM FAVOURITE WHERE USER_LOGINNAME = :pattern AND ATTRACTION_ID =:pattern2")
    LiveData<List<Favourite>>findFavByUserAndAttraction(String pattern,int pattern2);

    @Query("SELECT * FROM FAVOURITE WHERE USER_LOGINNAME = :pattern")
    LiveData<List<Favourite>>findFavByUser(String pattern);
}
