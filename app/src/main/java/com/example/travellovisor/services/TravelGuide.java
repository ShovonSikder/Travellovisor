package com.example.travellovisor.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TravelGuide extends AppCompatActivity {
ImageView search2;
TextView buttonToPkg;
ListView guideList;
FirebaseDatabase database;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guide);
        System.out.println(db);
        //references
        search2=findViewById(R.id.search2);
        buttonToPkg=findViewById(R.id.butonToPkg);
        guideList=findViewById(R.id.guideList);

        //get database reference
        database=FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db=database.getReference("travelguides");

       //Fetched data stored array
        ArrayList<Guides> guides= new ArrayList<Guides>();

        //Guides g1=new Guides("hi","Hello");
        //guides.add(g1);

        GuideListAdapter adapter=new GuideListAdapter(getApplicationContext(),guides);
        guideList.setAdapter(adapter);


        guideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //view description on another activity
                Intent intent=new Intent(getApplicationContext(),TravelGuideDetails.class);
                Bundle extras=new Bundle();
                extras.putString("title",guides.get(position).getTitle());
                extras.putString("descrip",guides.get(position).getDescription());
                extras.putString("imglink",guides.get(position).getImglink());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        //retrieve data from database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //on update clear previous data and store new
                guides.clear();
                for(DataSnapshot data: snapshot.getChildren()){

                    Guides g=data.getValue(Guides.class);
                    guides.add(g);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

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