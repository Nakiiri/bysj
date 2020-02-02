package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.traveldemo.Entity.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertOrder(Order...orders);

    @Delete
    void deleteOrder(Order...orders);

    @Query("SELECT * FROM `ORDER` WHERE USER_ID = :pattern AND ORDER_STATE != 0 ORDER BY ORDER_ID DESC")
    LiveData<List<Order>>findOrderByUserState12(String pattern);

    @Query("SELECT * FROM `ORDER` WHERE USER_ID = :pattern AND ORDER_STATE = 0 ORDER BY ORDER_ID DESC")
    LiveData<List<Order>>findOrderByUserState0(String pattern);

    @Query("SELECT * FROM `ORDER` WHERE AGENCY_LOGINNAME like :pattern AND ORDER_STATE = 0 ORDER BY ORDER_ID DESC")
    LiveData<List<Order>>findOrderByAgencyLogin(String pattern);

    @Query("SELECT * FROM `ORDER` WHERE USER_ID = :pattern AND PLAN_ID = :pattern2 AND ORDER_STATE = 0")
    LiveData<List<Order>>findOrderByUserAndPlan(String pattern,int pattern2);

    @Query("SELECT * FROM `ORDER` WHERE ORDER_ID = :pattern")
    LiveData<List<Order>>findOrderByID(int pattern);

    @Query("UPDATE `ORDER` SET ORDER_STATE = 1 WHERE ORDER_ID =:pattern")
    void updateOrderState1(int pattern);

    @Query("UPDATE `ORDER` SET ORDER_STATE = 2 WHERE ORDER_ID =:pattern")
    void updateOrderState2(int pattern);

    @Query("UPDATE `ORDER` SET ORDER_ISASSESS = 1 AND ORDER_ASSESS =  :pattern WHERE ORDER_ID =:pattern2")
    void updateOrderAssess(int pattern,int pattern2);

    @Query("SELECT * FROM `ORDER` WHERE AGENCY_LOGINNAME = :pattern")
    LiveData<List<Order>>findOrderByAgency(String pattern);

    @Query("SELECT * FROM `ORDER` WHERE PLAN_ID = :pattern")
    LiveData<List<Order>>findOrderByPlan(int pattern);



}
