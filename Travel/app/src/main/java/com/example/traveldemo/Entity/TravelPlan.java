package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = @ForeignKey(entity = TravelAgency.class,parentColumns = "login_name",childColumns = "agency_loginname",onDelete = 1,onUpdate = 1))
public class TravelPlan {
    @PrimaryKey(autoGenerate = true)
    private int plan_id;

    @ColumnInfo(name = "agency_loginname")
    private String agency_loginname;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "origin")
    private String origin;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "departure_time")
    private String departure_time;
    @ColumnInfo(name = "spend_time")
    private String spend_time;

    @ColumnInfo(name="plan_url")
    private String plan_url;
    @ColumnInfo(name="plan_title")
    private String plan_title;

    @ColumnInfo(name="plan_sale")
    private int plan_sale;
    @ColumnInfo(name="plan_mark")
    private float plan_mark;

    public TravelPlan(String agency_loginname, String country, String city, String origin, String price, String departure_time, String spend_time, String plan_url, String plan_title, int plan_sale, float plan_mark) {
        this.agency_loginname = agency_loginname;
        this.country = country;
        this.city = city;
        this.origin = origin;
        this.price = price;
        this.departure_time = departure_time;
        this.spend_time = spend_time;
        this.plan_url = plan_url;
        this.plan_title = plan_title;
        this.plan_sale = plan_sale;
        this.plan_mark = plan_mark;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getAgency_loginname() {
        return agency_loginname;
    }

    public void setAgency_loginname(String agency_loginname) {
        this.agency_loginname = agency_loginname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getSpend_time() {
        return spend_time;
    }

    public void setSpend_time(String spend_time) {
        this.spend_time = spend_time;
    }

    public String getPlan_url() {
        return plan_url;
    }

    public void setPlan_url(String plan_url) {
        this.plan_url = plan_url;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
    }

    public int getPlan_sale() {
        return plan_sale;
    }

    public void setPlan_sale(int plan_sale) {
        this.plan_sale = plan_sale;
    }

    public float getPlan_mark() {
        return plan_mark;
    }

    public void setPlan_mark(float plan_mark) {
        this.plan_mark = plan_mark;
    }
}

