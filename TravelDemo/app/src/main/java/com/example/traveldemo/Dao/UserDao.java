package com.example.traveldemo.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.traveldemo.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    Void insertUser(User...users);

    @Update
    Void updateUser(User...users);

    @Query("UPDATE USER SET USER_NAME =:value , GENDER =:value2 , SIGN =:value3 WHERE LOGIN_NAME =:value4")
    void updateUserBy(String value,String value2,String value3,String value4);

    @Query("SELECT * FROM USER ORDER BY USER_ID DESC")
    LiveData<List<User>>getAllUserLive();

    @Query("SELECT * FROM USER WHERE LOGIN_NAME LIKE :pattern ORDER BY USER_ID DESC")
        List<User> findUser(String pattern);


    @Query("SELECT * FROM USER WHERE LOGIN_NAME LIKE :pattern ORDER BY USER_ID DESC")
   // User findUserByLoginname(String pattern);
    LiveData<List<User>>findUserByLoginname(String pattern);



}
