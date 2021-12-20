package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //redirects to homepage;
        goToHomepage();

    }
    private void goToHomepage() {
        Intent intent=new Intent(this,Homepage.class);
        startActivity(intent);
        finish();
    }
}