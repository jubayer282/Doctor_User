package com.jubayer.doctorsappinmentsystemuser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class About_Us extends AppCompatActivity {

    private ImageView map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        /*action bar and title name*/
        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        map = findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenMapIntent());
            }
        });
    }
    private Intent getOpenMapIntent(){
        Uri uri = Uri.parse("https://www.google.com/maps/place/Bangladesh+University+of+Business+and+Technology+(BUBT)/@23.8131833,90.3550184,16z/data=!4m6!3m5!1s0x3755c12015382851:0x3ceca92fcf1a72d2!8m2!3d23.8117863!4d90.3569152!16s%2Fm%2F02w5tgs");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
        return intent;
    }
}