package com.example.travellovisor;

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
import com.example.travellovisor.services.BookedPackage;
import com.example.travellovisor.services.PackageTour;
import com.example.travellovisor.services.PkgListAdapter;
import com.example.travellovisor.services.TourPackageDetails;
import com.example.travellovisor.services.TravelGuide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class YourPackageList extends AppCompatActivity {
    ImageView search2,profile2;
    ListView pkgList1;
    ViewPager slider1;

    FirebaseDatabase database;
    DatabaseReference db, db2;

    FirebaseAuth nAuth;
    FirebaseUser nUser;

    private ArrayList<SlideImage> slides;
    private ArrayList<String> bookepkgId;
    private ArrayList<PackageTour> pkges;
    private ArrayList<PackageTour> Allpkg;
    SlideAdapter slideAdapter;
    PkgListAdapter pkgAdapter;

    String userid;

    int currentSlide = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_package_list);

        //references
        search2 = findViewById(R.id.search2);
        pkgList1 = findViewById(R.id.pkgList1);
        slider1 = findViewById(R.id.slider1);
        profile2=findViewById(R.id.profile2);


        Allpkg = new ArrayList<>();
        //adapter for slide show
        slides = new ArrayList<SlideImage>();

        slideAdapter = new SlideAdapter(getApplicationContext(), slides);
        slider1.setAdapter(slideAdapter);

        /*Timer task to perform auto scrolling using thread*/
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if (currentSlide == slides.size()) {
                    currentSlide = 0;
                } else {
                    currentSlide = slider1.getCurrentItem() + 1;
                }
                slider1.setCurrentItem(currentSlide, true);


            }
        };

        //timer schedules
        Timer timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 3000);//slide show end

        //get database reference
        database = FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db = database.getReference("tourpackages");
        db2 = database.getReference("bookedpackages");

        nAuth = FirebaseAuth.getInstance();
        nUser = nAuth.getCurrentUser();
        userid = nUser.getUid();

        //Fetched data storage array
        bookepkgId = new ArrayList<String>();

        pkges = new ArrayList<PackageTour>();
        pkgAdapter = new PkgListAdapter(getApplicationContext(), pkges);
        pkgList1.setAdapter(pkgAdapter);
//checkUpdate();
        pkgList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), YourTourPackage.class);
                intent.putExtra("pkg", (Serializable) pkges.get(position));
                startActivity(intent);
                finish();
            }
        });

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading info...");
        progress.show();

        //filter booked packages for current user
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progress.show();
                bookepkgId.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    BookedPackage bpkg = data.getValue(BookedPackage.class);
                    if (bpkg.getUserId().equals(userid)) {
                        bookepkgId.add(bpkg.getPkgId());

                    }
                }
                checkUpdate();

//                Toast.makeText(getApplicationContext(),""+bookepkgId.size(),Toast.LENGTH_LONG).show();
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Allpkg.clear();
                progress.show();
                for (DataSnapshot data1 : snapshot.getChildren()) {
                    PackageTour ppkg = data1.getValue(PackageTour.class);
                    Allpkg.add(ppkg);
                }
                checkUpdate();
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UsersProfile.class));
                finish();
            }
        });
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                finish();
            }
        });

    }

    private void checkUpdate() {
        pkges.clear();
        slides.clear();
        for (int i = 0; i < bookepkgId.size(); i++) {
            for (int j = 0; j < Allpkg.size(); j++) {
                if (Allpkg.get(j).getPkgid().equals(bookepkgId.get(i))) {
                    pkges.add(Allpkg.get(j));
                    slides.add(new SlideImage(Allpkg.get(j).getImglink()));
                }
            }
        }
        pkgAdapter.notifyDataSetChanged();
        slideAdapter.notifyDataSetChanged();


    }


}