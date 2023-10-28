package com.jubayer.doctorsappinmentsystemuser.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_doctors")
public class FavouriteDoctor {
    @PrimaryKey (autoGenerate = true)
    private long id;
    private String doctorId;
    public FavouriteDoctor(String doctorId) {
        this.doctorId = doctorId;
    }
    public long getId() {
        return id;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}

