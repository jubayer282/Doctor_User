package com.jubayer.doctorsappinmentsystemuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jubayer.doctorsappinmentsystemuser.models.AppoinmentData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetAppoinmentActivity extends AppCompatActivity {

    TextInputEditText nameEt, timeEt, mobileEt, dayEt;
    Button submitBtn;
    TextView unapprove, dr_nameTv ;
    String drname;
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
        dr_nameTv = findViewById(R.id.dr_nameTv);

        drname = getIntent().getStringExtra("dr_name");

        dr_nameTv.setText(drname);

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
        //String time = timeEt.getText().toString().trim();
        String day = dayEt.getText().toString().trim();
        String status = unapprove.getText().toString().trim();


        if (!name.isEmpty() && !phone.isEmpty()) {

            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            String date = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(calForTime.getTime());
            String appointmentId = databaseReference.push().getKey();

            AppoinmentData appoinmentData = new AppoinmentData(appointmentId,name, phone, time, day, status, date, FirebaseAuth.getInstance().getCurrentUser().getUid(), drname);

            databaseReference.child(appointmentId).setValue(appoinmentData);

            Toast.makeText(this, "Appointment request complete", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Appointment request is no complete", Toast.LENGTH_SHORT).show();
        }
    }
}