package com.example.travellovisor.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.R;
import com.example.travellovisor.account.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TourPackageDetails extends AppCompatActivity {
    TextView pkgid, pkgname, pkgcost, traveldate, minimumperson, duration, hotel, btnsubmit;
    RadioGroup bedroom, transport;

    FirebaseAuth nAuth;
    FirebaseUser nUser;
    FirebaseDatabase database;
    DatabaseReference db;

    String bedroomtype="",transporttype="";
    PackageTour pkg;
    ProgressDialog progress;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_package_details);

        //reference area
        pkgid = findViewById(R.id.pkgid);
        pkgname = findViewById(R.id.pkgname);
        pkgcost = findViewById(R.id.pkgcost);
        traveldate = findViewById(R.id.traveldate);
        minimumperson = findViewById(R.id.minimumperson);
        duration = findViewById(R.id.pkgduration);
        hotel = findViewById(R.id.hotel);
        btnsubmit = findViewById(R.id.btnsubmit);
        bedroom = findViewById(R.id.bedroom);
        transport = findViewById(R.id.transport);

        pkg = (PackageTour) getIntent().getSerializableExtra("pkg");
        display();

        nAuth = FirebaseAuth.getInstance();
        nUser = nAuth.getCurrentUser();

        database=FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db=database.getReference("bookedpackages");

        progress = new ProgressDialog(this);
        transport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.bus) transporttype="Bus";
                else if (checkedId==R.id.train) transporttype="Train";
                else if (checkedId==R.id.air) transporttype="Air";

            }
        });

        bedroom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.bsnscls) bedroomtype="Business Class";
                else if (checkedId==R.id.ecocls) bedroomtype="Economic Class";
                else if (checkedId==R.id.normalcls) bedroomtype="Normal Class";
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nUser == null) {
                    goToLogin();
                }
                else if (bedroomtype.equals("") || transporttype.equals("")){
                    Toast.makeText(getApplicationContext(),"Extra info must be selected",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                    bookThePackage();
            }

        });
    }

    private void display() {
        pkgname.setText(pkg.getPkgname());
        pkgid.setText(pkgid.getText() + pkg.getPkgid());
        pkgcost.setText(pkgcost.getText() + pkg.getPkgcost() + " BDT / P");
        duration.setText(duration.getText() + pkg.getPkgduration());
        traveldate.setText(traveldate.getText() + pkg.getTraveldate());
        minimumperson.setText(minimumperson.getText() + pkg.getMinimumperson() + " minimum");
        hotel.setText(hotel.getText() + pkg.getHotel());


    }


    private void bookThePackage() {

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm the purchase!");
        builder.setIcon(R.drawable.ic_baseline_cloud_upload_24);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                progress.setTitle("Data Upload");
                progress.setMessage("Uploading info...");
                progress.show();
                //upload to database

                        String key=db.push().getKey();
                        db.child(key).setValue(new BookedPackage(nUser.getUid(),pkg.getPkgid(),"Due"));
                        Toast.makeText(getApplicationContext(), "Successfully booked", Toast.LENGTH_LONG).show();
                        progress.dismiss();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //terminate
                Toast.makeText(getApplicationContext(), "Not Successful", Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();

    }

    private void goToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("reqFrom", "TPD");
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login Requires", Toast.LENGTH_LONG).show();
    }


}