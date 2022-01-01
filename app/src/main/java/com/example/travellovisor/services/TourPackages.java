package com.example.travellovisor.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.example.travellovisor.SlideAdapter;
import com.example.travellovisor.SlideImage;
import com.example.travellovisor.UsersProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TourPackages extends AppCompatActivity {
    ImageView search2,profile2;
    TextView buttonToGuide;
    ListView pkgList;
    ViewPager slider;

    FirebaseDatabase database;
    DatabaseReference db;

    int currentSlide=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_packages);

        //references
        search2=findViewById(R.id.search2);
        buttonToGuide=findViewById(R.id.butonToGuide);
        pkgList=findViewById(R.id.pkgList);
        slider=findViewById(R.id.slider);
        profile2=findViewById(R.id.profile2);


        //adapter for slide show
        ArrayList<SlideImage> slides=new ArrayList<SlideImage>();

        SlideAdapter slideAdapter=new SlideAdapter(getApplicationContext(),slides);
        slider.setAdapter(slideAdapter);

        /*Timer task to perform auto scrolling using thread*/
        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {

                if(currentSlide==slides.size()){
                    currentSlide=0;
                }
                else {
                    currentSlide = slider.getCurrentItem()+1;
                }
                slider.setCurrentItem(currentSlide, true);


            }
        };

        //timer schedules
        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 3000);

        //get database reference
        database=FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db=database.getReference("tourpackages");

        //Fetched data storage array
        ArrayList<PackageTour> pkges= new ArrayList<PackageTour>();

        PkgListAdapter pkgAdapter=new PkgListAdapter(getApplicationContext(),pkges);
        pkgList.setAdapter(pkgAdapter);

        pkgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),TourPackageDetails.class);
                intent.putExtra("pkg", (Serializable) pkges.get(position));
                startActivity(intent);
            }
        });

        ProgressDialog progress=new ProgressDialog(this);
        progress.setMessage("Loading info...");
        progress.show();


        //retrieve data from database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //on update clear previous data and store new
                pkges.clear();
                slides.clear();
                progress.show();
                for(DataSnapshot data: snapshot.getChildren()){

                    PackageTour pkg=data.getValue(PackageTour.class);
                    pkges.add(pkg);
                    slides.add(new SlideImage(pkg.getImglink()));

                }
                pkgAdapter.notifyDataSetChanged();
                slideAdapter.notifyDataSetChanged();
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });


        buttonToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TravelGuide.class));
                finish();
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

        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UsersProfile.class));
            }
        });
    }


}