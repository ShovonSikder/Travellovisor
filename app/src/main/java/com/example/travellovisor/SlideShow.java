package com.example.travellovisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SlideShow extends AppCompatActivity {
    ViewPager slider;
    int currentSlide=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        FirebaseDatabase database=FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference db=database.getReference("slideimgs");

        //references
        slider=findViewById(R.id.slider);


        //adapter
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




        //event listener for slideimgs references. retrieve from database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slides.clear();
                for(DataSnapshot data:snapshot.getChildren()){
                    slides.add( data.getValue(SlideImage.class));
                }
                slideAdapter.notifyDataSetChanged();
                slider.setAdapter(slideAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }
}