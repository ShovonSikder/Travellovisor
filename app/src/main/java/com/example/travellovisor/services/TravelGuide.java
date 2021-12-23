package com.example.travellovisor.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;

import java.util.ArrayList;

public class TravelGuide extends AppCompatActivity {
ImageView search2;
TextView buttonToPkg;
ListView guideList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guide);

        //reff
        search2=findViewById(R.id.search2);
        buttonToPkg=findViewById(R.id.butonToPkg);
        guideList=findViewById(R.id.guideList);

       //temporary
        ArrayList<String> titles= new ArrayList<String>();
        ArrayList<String> descriptions= new ArrayList<String>();
        titles.add("TT");titles.add("TT2 from DB");
        descriptions.add("DD");descriptions.add("DD2 freom DB");
        GuideListAdapter adapter=new GuideListAdapter(getApplicationContext(),titles,descriptions);
        guideList.setAdapter(adapter);

        guideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),TravelGuideDetails.class);

                startActivity(intent);
            }
        });
        //redirect to tour package
        buttonToPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_LONG).show();
            }
        });
        //redirect to homepage on click on search icon
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}