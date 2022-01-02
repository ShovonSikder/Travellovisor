package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.travellovisor.account.LoginActivity;
import com.example.travellovisor.account.RegistrationActivity;
import com.example.travellovisor.services.TourPackageDetails;
import com.example.travellovisor.services.TourPackages;
import com.example.travellovisor.services.TravelGuide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //redirects to homepage;
        //goToHomepage();
        //goToTravelGuide();
       // goToRegistrationActivity();
        //goToLogin();
        //goToTourPackages();
        //goToSlidShow();
        //goToUserProfile();
        //goToYourTourPackage();



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
    private void goToLogin() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToSlidShow() {
        Intent intent=new Intent(this, SlideShow.class);
        startActivity(intent);
        finish();
    }

    private void goToTourPackages(){
        startActivity(new Intent(getApplicationContext(), TourPackages.class));
        finish();
    }
    private void goToTourPackagesDetails(){
        startActivity(new Intent(getApplicationContext(), TourPackageDetails.class));
        finish();
    }
    private void goToUserProfile(){
        startActivity(new Intent(getApplicationContext(), UsersProfile.class));
        finish();
    }
    private void goToYourTourPackage(){
        startActivity(new Intent(getApplicationContext(), YourTourPackage.class));
        finish();
    }


}