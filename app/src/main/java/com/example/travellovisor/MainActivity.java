package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travellovisor.account.OTPsendActivity;
import com.example.travellovisor.services.TravelGuide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //redirects to homepage;
        //goToHomepage();
        //goToOtpSend();

    }

    private void goToOtpSend() {
        Intent intent=new Intent(this, OTPsendActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToHomepage() {
        Intent intent=new Intent(this, Homepage.class);
        startActivity(intent);
        finish();
    }
}