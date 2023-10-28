package com.jubayer.doctorsappinmentsystemuser.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoctorDao {
    @Insert
    long insert(FavouriteDoctor doctor);

    @Query("DELETE FROM favourite_doctors WHERE doctorId = :id")
    void delete(String id);


    @Query("SELECT * FROM favourite_doctors")
    List<FavouriteDoctor> getAll();

    @Query("SELECT * FROM favourite_doctors WHERE doctorId = :favouriteName")
    FavouriteDoctor getFavourite(String favouriteName);

}
