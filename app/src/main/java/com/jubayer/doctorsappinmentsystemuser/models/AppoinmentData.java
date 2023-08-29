package com.jubayer.doctorsappinmentsystemuser.models;

public class AppoinmentData {

    String name, mobile, time, day, status;

    public AppoinmentData() {
    }

    public AppoinmentData(String name, String mobile, String time, String day, String status) {
        this.name = name;
        this.mobile = mobile;
        this.time = time;
        this.day = day;
        this.status = status;
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
}
