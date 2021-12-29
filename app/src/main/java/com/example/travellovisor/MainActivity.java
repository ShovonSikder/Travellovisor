package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.travellovisor.account.LoginActivity;
import com.example.travellovisor.account.RegistrationActivity;
import com.example.travellovisor.services.TravelGuide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //redirects to homepage;
        goToHomepage();
        //goToTravelGuide();
        //goToRegistrationActivity();


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
}