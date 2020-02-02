package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mark {
    @PrimaryKey(autoGenerate = true)
    private int mark_id;

    @ColumnInfo(name="plan_id")
    private int plan_id;
    @ColumnInfo(name="userlogin")
    private String user_loginname;

    public Mark(int plan_id, String user_loginname) {
        this.plan_id = plan_id;
        this.user_loginname = user_loginname;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getUser_loginname() {
        return user_loginname;
    }

    public void setUser_loginname(String user_loginname) {
        this.user_loginname = user_loginname;
    }
}
