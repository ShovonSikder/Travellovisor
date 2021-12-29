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
TextView title,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guide_details);
//        Intent intenttest=new Intent(getApplicationContext(),TravelGuide.class);
//        startActivity(intenttest);
        //reference area
        search1=findViewById(R.id.search1);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        themeImage=findViewById(R.id.themeImage);

        //catch data passed through intent and display it
        Bundle extras=getIntent().getExtras();
        title.setText(extras.getString("title"));
        description.setText(extras.getString("descrip"));
        String imglink=extras.getString("imglink");
        if(!imglink.equals("")){
            //display the image
            Picasso.get().load(imglink).into(themeImage);
            imglink="";

        }
        else{
            //set the image source to default value
            themeImage.setImageResource(R.drawable.image1);
        }

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