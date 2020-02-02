package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favourite {
    @PrimaryKey(autoGenerate = true)
    private int favourite_id;

    @ColumnInfo(name="user_loginname")
    private String user_loginname;
    @ColumnInfo(name="attraction_id")
    private int attraction_id;

    public Favourite(String user_loginname, int attraction_id) {
        this.user_loginname = user_loginname;
        this.attraction_id = attraction_id;
    }

    public int getFavourite_id() {
        return favourite_id;
    }

    public void setFavourite_id(int favourite_id) {
        this.favourite_id = favourite_id;
    }

    public String getUser_loginname() {
        return user_loginname;
    }

    public void setUser_loginname(String user_loginname) {
        this.user_loginname = user_loginname;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }
}
