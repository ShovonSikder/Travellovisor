package com.example.travellovisor.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;

public class TravelGuideDetails extends AppCompatActivity {
ImageView search1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guide_details);
//        Intent intenttest=new Intent(getApplicationContext(),TravelGuide.class);
//        startActivity(intenttest);
        //reference area
        search1=findViewById(R.id.search1);

        //redirect to homepage on click on search icon
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Homepage.class);
                startActivity(intent);
                finish();
            }
        });



    }


}