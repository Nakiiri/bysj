package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.traveldemo.Entity.Attraction;

import java.util.List;

@Dao
public interface AttractionDao {
    @Insert
    void insertAttraction(Attraction...attractions);

    @Delete
    void deleteAttraction(Attraction...attractions);

    @Query("SELECT * FROM ATTRACTION WHERE PLAN_ID LIKE :pattern ORDER BY ATTRACTION_ID DESC")
    LiveData<List<Attraction>>findAttractionByPlan(int pattern);

    @Query("UPDATE ATTRACTION SET ATTRACTION_NAME =:value , ATTRACTION_OPEN =:value2 , ATTRACTION_DES =:value3 ,ATTRACTION_POS =:value4 ,ATTRACTION_ENJOY =:value5 ,IMAGE_URL =:value6 WHERE ATTRACTION_ID =:value7")
    void updateAttraction(String value,String value2,String value3,String value4,String value5,String value6,int value7);

    @Query("SELECT * FROM ATTRACTION ORDER BY FAVOURITE DESC")
    LiveData<List<Attraction>>findAllAttraction();

    @Query("UPDATE ATTRACTION SET FAVOURITE =:pattern WHERE ATTRACTION_ID =:pattern2")
    void updateAttractionFavourite(int pattern,int pattern2);

    @Query("SELECT * FROM ATTRACTION WHERE ATTRACTION_ID LIKE :pattern")
    LiveData<List<Attraction>>findAttractionByID(int pattern);

    @Query("UPDATE ATTRACTION SET favourite = favourite+1 WHERE ATTRACTION_ID =:pattern")
    void updateFavAdd(int pattern);

    @Query("UPDATE ATTRACTION SET favourite = favourite-1 WHERE ATTRACTION_ID =:pattern")
    void updateFavMinus(int pattern);


}
