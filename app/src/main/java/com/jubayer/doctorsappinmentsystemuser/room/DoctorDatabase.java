package com.jubayer.doctorsappinmentsystemuser.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FavouriteDoctor.class}, version = 1, exportSchema = false)
public abstract class DoctorDatabase extends RoomDatabase {
   /* private static RecipeDatabase instance;*/

    public static DoctorDatabase getInstance() {
        return instance;
    }

    public static void setInstance(DoctorDatabase instance) {
        DoctorDatabase.instance  =instance;
    }

    public abstract DoctorDao favouriteDao();
    private static DoctorDatabase instance = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static  synchronized DoctorDatabase getInstance(Context context) {
        if (getInstance() == null) {
            setInstance(Room.databaseBuilder(context.getApplicationContext(), DoctorDatabase.class, "doctor_database").fallbackToDestructiveMigration().build());
        }
        return getInstance();
    }

}
