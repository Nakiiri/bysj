package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.traveldemo.Entity.TravelPlan;



@Dao
public interface TravelPlanDao {
    @Insert
    void insertPlan(TravelPlan...travelPlans);

    @Delete
    void deletePlan(TravelPlan...travelPlans);

    @Query("SELECT * FROM TRAVELPLAN WHERE AGENCY_LOGINNAME LIKE :pattern ORDER BY PLAN_ID DESC")
    LiveData<List<TravelPlan>>findPlanByAgency(String pattern);

    @Query("SELECT * FROM TRAVELPLAN ORDER BY PLAN_ID DESC")
    LiveData<List<TravelPlan>>allPlan();

    @Query("SELECT * FROM TRAVELPLAN WHERE CITY LIKE :pattern ORDER BY PLAN_ID DESC")
    LiveData<List<TravelPlan>>findPlanByCity(String pattern);

    @Query("SELECT * FROM TRAVELPLAN WHERE PLAN_ID = :pattern")
    LiveData<List<TravelPlan>>findPlanByID(int pattern);

    @Query("SELECT * FROM TRAVELPLAN ORDER BY CAST(PRICE as int) DESC")
    LiveData<List<TravelPlan>>allPlanPriceDesc();

    @Query("SELECT * FROM TRAVELPLAN ORDER BY CAST (PRICE as int) ASC")
    LiveData<List<TravelPlan>>allPlanPrice();

    @Query("SELECT * FROM TRAVELPLAN ORDER BY PLAN_SALE DESC")
    LiveData<List<TravelPlan>>allPlanSaleDesc();

    @Query("SELECT * FROM TRAVELPLAN ORDER BY PLAN_MARK DESC")
    LiveData<List<TravelPlan>>allPlanMarkDesc();



}
