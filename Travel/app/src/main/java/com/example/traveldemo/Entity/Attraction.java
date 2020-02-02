package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = @ForeignKey(entity = TravelPlan.class,parentColumns = "plan_id",childColumns = "plan_id",onDelete = 5,onUpdate = 5))
public class Attraction {
    @PrimaryKey(autoGenerate = true)
    private int attraction_id;

    @ColumnInfo(name = "plan_id")
    private int plan_id;
    @ColumnInfo(name = "attraction_name")
    private String attraction_name;
    @ColumnInfo(name = "attraction_open")
    private String attraction_opentime;
    @ColumnInfo(name = "attraction_des")
    private String attraction_description;
    @ColumnInfo(name = "attraction_pos")
    private String attraction_position;
    @ColumnInfo(name = "attraction_enjoy")
    private String attraction_enjoytime;
    @ColumnInfo(name = "image_url")
    private String image_url;
    @ColumnInfo(name = "favourite")
    private int favourite;
    @ColumnInfo(name = "huge_image")
    private String huge_image;
    @ColumnInfo(name = "des_detail")
    private String des_detail;

    public Attraction(int plan_id, String attraction_name, String attraction_opentime, String attraction_description, String attraction_position, String attraction_enjoytime, String image_url, int favourite, String huge_image, String des_detail) {
        this.plan_id = plan_id;
        this.attraction_name = attraction_name;
        this.attraction_opentime = attraction_opentime;
        this.attraction_description = attraction_description;
        this.attraction_position = attraction_position;
        this.attraction_enjoytime = attraction_enjoytime;
        this.image_url = image_url;
        this.favourite = favourite;
        this.huge_image = huge_image;
        this.des_detail = des_detail;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public String getAttraction_opentime() {
        return attraction_opentime;
    }

    public void setAttraction_opentime(String attraction_opentime) {
        this.attraction_opentime = attraction_opentime;
    }

    public String getAttraction_description() {
        return attraction_description;
    }

    public void setAttraction_description(String attraction_description) {
        this.attraction_description = attraction_description;
    }

    public String getAttraction_position() {
        return attraction_position;
    }

    public void setAttraction_position(String attraction_position) {
        this.attraction_position = attraction_position;
    }

    public String getAttraction_enjoytime() {
        return attraction_enjoytime;
    }

    public void setAttraction_enjoytime(String attraction_enjoytime) {
        this.attraction_enjoytime = attraction_enjoytime;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getHuge_image() {
        return huge_image;
    }

    public void setHuge_image(String huge_image) {
        this.huge_image = huge_image;
    }

    public String getDes_detail() {
        return des_detail;
    }

    public void setDes_detail(String des_detail) {
        this.des_detail = des_detail;
    }
}
