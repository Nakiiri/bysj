package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.traveldemo.Entity.TravelAgency;



@Dao
public interface TravelAgencyDao {
    @Insert
    void insertAgency(TravelAgency...travelAgencies);

    @Query("SELECT * FROM TRAVELAGENCY WHERE LOGIN_NAME LIKE :pattern ORDER BY AGENCY_ID DESC")
    LiveData<List<TravelAgency>>findAgencyByLogin(String pattern);

    @Query("SELECT * FROM TRAVELAGENCY ORDER BY AGENCY_ID DESC")
    LiveData<List<TravelAgency>>getAllAgency();

//    @Query("SELECT * FROM TRAVELAGENCY WHERE LOGIN_NAME = :pattern ORDER BY AGENCY_ID DESC")
//    TravelAgency findAgencyByLoginname(String pattern);
}
