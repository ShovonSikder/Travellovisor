package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travellovisor.services.TravelGuide;
import com.example.travellovisor.services.TravelGuideDetails;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //redirects to homepage;
        //goToHomepage();
        //goToTravelGuide();
        goToSlideShow();

    }
    private void goToHomepage() {
        Intent intent=new Intent(this, Homepage.class);
        startActivity(intent);
        finish();
    }
    private void goToTravelGuide() {
        Intent intent=new Intent(this, TravelGuide.class);
        startActivity(intent);
        finish();
    }
    private void goToSlideShow() {
        Intent intent=new Intent(this, SlideShow.class);
        startActivity(intent);
        finish();
    }
}