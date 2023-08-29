package com.jubayer.doctorsappinmentsystemuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jubayer.doctorsappinmentsystemuser.models.AppoinmentData;

public class GetAppoinmentActivity extends AppCompatActivity {

    TextInputEditText nameEt, timeEt, mobileEt, dayEt;
    Button submitBtn;

    TextView unapprove;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appoinment);

        databaseReference = FirebaseDatabase.getInstance().getReference("appointment");

        nameEt = findViewById(R.id.nameEt);
        timeEt = findViewById(R.id.timeEt);
        mobileEt = findViewById(R.id.phoneEt);
        dayEt = findViewById(R.id.dayEt);
        unapprove = findViewById(R.id.unapprove);

        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

    }

    private void uploadData() {

        String name = nameEt.getText().toString().trim();
        String phone = mobileEt.getText().toString().trim();
        String time = timeEt.getText().toString().trim();
        String day = dayEt.getText().toString().trim();
        String status = unapprove.getText().toString().trim();


        if (!name.isEmpty() && !phone.isEmpty()) {

            String userId = databaseReference.push().getKey();
            AppoinmentData appoinmentData = new AppoinmentData(name, phone, time, day, status);
            databaseReference.child(userId).setValue(appoinmentData);

            Toast.makeText(this, "Appointment request complete", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Appointment request is no complete", Toast.LENGTH_SHORT).show();
        }
    }
}