package com.jubayer.doctorsappinmentsystemuser.models;

public class AppoinmentData {

    String name, mobile, time, day, status, date, uid, drname;

    public AppoinmentData() {
    }

    public AppoinmentData(String name, String mobile, String time, String day, String status, String date, String uid, String drname) {
        this.name = name;
        this.mobile = mobile;
        this.time = time;
        this.day = day;
        this.status = status;
        this.date = date;
        this.uid = uid;
        this.drname = drname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }
}
