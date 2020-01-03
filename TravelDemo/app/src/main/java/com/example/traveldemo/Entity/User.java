package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "login_name",childColumns = "user_name",onUpdate = 5))
public class User {
    @PrimaryKey(autoGenerate = true)
    private int user_id;

    @ColumnInfo(name="login_name")
    private String login_name;
    @ColumnInfo(name="login_password")
    private String login_password;
    @ColumnInfo(name="user_name")
    private String user_name;
    @ColumnInfo(name="gender")
    private  String gender;
    @ColumnInfo(name="sign")
    private String sign;



    public User(String login_name, String login_password, String user_name, String gender, String sign) {
        this.login_name = login_name;
        this.login_password = login_password;
        this.user_name = user_name;
        this.gender = gender;
        this.sign = sign;
    }

//    public User(int user_id, String login_name, String login_password, String user_name, String gender, String sign) {
//        this.user_id = user_id;
//        this.login_name = login_name;
//        this.login_password = login_password;
//        this.user_name = user_name;
//        this.gender = gender;
//        this.sign = sign;
//    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
