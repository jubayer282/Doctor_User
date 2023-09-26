package com.jubayer.doctorsappinmentsystemuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jubayer.doctorsappinmentsystemuser.adapter.AppointmentAdapter;
import com.jubayer.doctorsappinmentsystemuser.models.AppoinmentData;

import java.util.ArrayList;

public class AppoinmentDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    DatabaseReference reference;

    ArrayList<AppoinmentData> model;

    AppointmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_details);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        model = new ArrayList<>();

        /*adapter set on model*/
        adapter = new AppointmentAdapter(getApplicationContext(), model);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        /*database child and get data from database*/
        reference = FirebaseDatabase.getInstance().getReference().child("appointment");

        loadData();

    }

    private void loadData() {

        Query query = reference.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(AppoinmentDetailsActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {

                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        AppoinmentData data = snapshot.getValue(AppoinmentData.class);
                        model.add(0, data);
                    }

                    /*data visible code*/
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AppoinmentDetailsActivity.this));
                    adapter = new AppointmentAdapter(AppoinmentDetailsActivity.this, model);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AppoinmentDetailsActivity.this, "Data Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
