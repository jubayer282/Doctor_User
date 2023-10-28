package com.jubayer.doctorsappinmentsystemuser.room;

import android.app.Application;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DoctorRepository {
    private DoctorDao doctorDao;
    public DoctorRepository(Application application) {
        DoctorDatabase database = DoctorDatabase.getInstance(application);
        doctorDao = database.favouriteDao();
    }

    public long insert(FavouriteDoctor doctor) {
        Future<Long> future = DoctorDatabase.databaseWriteExecutor.submit(() -> doctorDao.insert(doctor));

        try {
            return future.get(); // wait for result
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return -1;
        }
    }
     public void delete(FavouriteDoctor doctor) {
        DoctorDatabase.databaseWriteExecutor.submit(() -> doctorDao.delete(doctor.getDoctorId()));
     }

    public boolean isFavourite(String favouriteDoctor) {
        Future<Boolean> future = DoctorDatabase.databaseWriteExecutor.submit(() -> doctorDao.getFavourite(favouriteDoctor) != null);

        try {
            return future.get(); // wait for result
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return false;
        }

    }
}
