package com.example.travellovisor.services;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.squareup.picasso.Picasso;

public class TravelGuideDetails extends AppCompatActivity {
ImageView search1,themeImage;
TextView title,description,butonToPkg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guide_details);

        //reference area
        search1=findViewById(R.id.search1);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        themeImage=findViewById(R.id.themeImage);
        butonToPkg=findViewById(R.id.butonToPkg);

        //catch data passed through intent and display it
        displayInfo();


        //redirect to tour packages
        butonToPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TourPackages.class));
                finish();
            }
        });
        //redirect to homepage on click on search icon
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Homepage.class));
                finish();
            }
        });





    }

    private void displayInfo(){

        Bundle extras=getIntent().getExtras();
        title.setText(extras.getString("title"));
        description.setText(extras.getString("descrip"));


        if(!extras.getString("imglink").equals("")){
            //display the image
            Picasso.get().load(extras.getString("imglink")).into(themeImage);
        }
        else{
            //set the image source to default value if no image link found
            themeImage.setImageResource(R.drawable.image1);
        }
    }


}