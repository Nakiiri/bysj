package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.traveldemo.Entity.Mark;

import java.util.List;

@Dao
public interface MarkDao {
    @Insert
    void insertMark(Mark...marks);

    @Delete
    void deleteMark(Mark...marks);

    @Query("SELECT * FROM MARK WHERE PLAN_ID =:pattern")
    LiveData<List<Mark>>findMarkByPlan(int pattern);

    @Query("SELECT * FROM MARK WHERE USERLOGIN =:pattern")
    LiveData<List<Mark>>findMarkByUser(String pattern);


}
