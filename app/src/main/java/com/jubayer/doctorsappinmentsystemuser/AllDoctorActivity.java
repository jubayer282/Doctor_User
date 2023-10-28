package com.jubayer.doctorsappinmentsystemuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jubayer.doctorsappinmentsystemuser.adapter.DoctorAdapter;
import com.jubayer.doctorsappinmentsystemuser.databinding.ActivityAllDoctorBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class AllDoctorActivity extends AppCompatActivity {
    ActivityAllDoctorBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("Doctors");
        binding.rvDoctors.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvDoctors.setAdapter(new DoctorAdapter());
        String type = getIntent().getStringExtra("type");
        if (type.equalsIgnoreCase("category")) {
            filterByCategory();
        } else if (type.equalsIgnoreCase("search")) {
            loadByDoctors();
        } else {
            loadAllDoctors();
        }
    }

    private void loadByDoctors() {
        // Search doctor name
        String query = getIntent().getStringExtra("query");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Doctor> doctors = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    if (doctor.getName().toLowerCase().contains(query.toLowerCase()))
                        doctors.add(doctor);
                }
                DoctorAdapter adapter = (DoctorAdapter) binding.rvDoctors.getAdapter();
                if (adapter != null) {
                    adapter.setDoctorList(doctors);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.getMessage());
            }
        });

    }

    private void loadAllDoctors() {
        // load all doctors
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Doctor> doctors = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                Collections.shuffle(doctors);
                DoctorAdapter adapter = (DoctorAdapter) binding.rvDoctors.getAdapter();
                if (adapter != null) {
                    adapter.setDoctorList(doctors);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.getMessage());
            }
        });
    }

    private void filterByCategory() {
        // Filter doctors by category
        String category = getIntent().getStringExtra("category");
        reference.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Doctor> doctors = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                DoctorAdapter adapter = (DoctorAdapter) binding.rvDoctors.getAdapter();
                if (adapter != null) {
                    adapter.setDoctorList(doctors);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.getMessage());
            }
        });
    }

}