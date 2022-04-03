package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchNow extends AppCompatActivity {

    TextView texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_now);

        texts=findViewById(R.id.texts);

        Intent extra=getIntent();

        texts.setText("From: "+extra.getStringExtra("src")+"\n\nTo: "+extra.getStringExtra("dest")+"\n\nDate: "+extra.getStringExtra("date"));

    }
}