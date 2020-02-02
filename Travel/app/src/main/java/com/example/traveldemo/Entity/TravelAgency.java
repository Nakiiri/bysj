package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TravelAgency {
    @PrimaryKey(autoGenerate = true)
    private int agency_id;

    @ColumnInfo(name = "login_name")
    private String login_name;
    @ColumnInfo(name = "login_password")
    private String login_password;
    @ColumnInfo(name = "agency_name")
    private String agency_name;


    public TravelAgency(String login_name, String login_password, String agency_name) {
        this.login_name = login_name;
        this.login_password = login_password;
        this.agency_name = agency_name;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }
}
