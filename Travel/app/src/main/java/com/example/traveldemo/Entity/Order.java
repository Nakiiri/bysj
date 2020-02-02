package com.example.traveldemo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int order_id;


    @ColumnInfo(name="user_id")
    private String user_loginname;
    @ColumnInfo(name="plan_id")
    private int plan_id;
    @ColumnInfo(name="agency_loginname")
    private String agency_loginname;
    @ColumnInfo(name="order_state")
    private int order_state;//0：预订成功  1：取消订单  2：确认完成订单
    @ColumnInfo(name = "order_isassess")
    private int order_isassess;//是否评价  0：未评价  1：已评价
    @ColumnInfo(name="order_assess")
    private int order_assess;
    @ColumnInfo(name = "order_mark")
    private String order_mark;

    public Order(String user_loginname, int plan_id, String agency_loginname, int order_state, int order_isassess, int order_assess, String order_mark) {
        this.user_loginname = user_loginname;
        this.plan_id = plan_id;
        this.agency_loginname = agency_loginname;
        this.order_state = order_state;
        this.order_isassess = order_isassess;
        this.order_assess = order_assess;
        this.order_mark = order_mark;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUser_loginname() {
        return user_loginname;
    }

    public void setUser_loginname(String user_loginname) {
        this.user_loginname = user_loginname;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public int getOrder_isassess() {
        return order_isassess;
    }

    public void setOrder_isassess(int order_isassess) {
        this.order_isassess = order_isassess;
    }

    public int getOrder_assess() {
        return order_assess;
    }

    public void setOrder_assess(int order_assess) {
        this.order_assess = order_assess;
    }

    public String getAgency_loginname() {
        return agency_loginname;
    }

    public void setAgency_loginname(String agency_loginname) {
        this.agency_loginname = agency_loginname;
    }

    public String getOrder_mark() {
        return order_mark;
    }

    public void setOrder_mark(String order_mark) {
        this.order_mark = order_mark;
    }
}
