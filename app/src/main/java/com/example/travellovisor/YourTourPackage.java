package com.example.travellovisor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.services.BookedPackage;
import com.example.travellovisor.services.PackageTour;
import com.example.travellovisor.services.TourPackages;

public class YourTourPackage extends AppCompatActivity {
TextView tourpkg,ypkgid, ypkgname, ypkgcost, ytraveldate, yminimumperson, yduration, yhotel;
Button pkgdelet;

PackageTour pkg;

    ProgressDialog progress;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_tour_package);

        //ref
        tourpkg=findViewById(R.id.tourpkg);
        pkgdelet=findViewById(R.id.pkgdelete);
        ypkgid = findViewById(R.id.ypkgid);
        ypkgname = findViewById(R.id.ypkgname);
        ypkgcost = findViewById(R.id.ypkgcost);
        ytraveldate = findViewById(R.id.ytraveldate);
        yminimumperson = findViewById(R.id.yminimumperson);
        yduration = findViewById(R.id.ypkgduration);
        yhotel = findViewById(R.id.yhotel);

        progress = new ProgressDialog(this);

        pkg= (PackageTour) getIntent().getSerializableExtra("pkg");

        display();

        tourpkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TourPackages.class));
                finish();
            }
        });

        pkgdelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProcedures();
            }
        });
    }
    private void deleteProcedures(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Demo.Are you sure?");
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                progress.setMessage("Deleting info...");
                progress.show();
                //upload to database


                Toast.makeText(getApplicationContext(), "Successfully Deleted. Not implemented", Toast.LENGTH_LONG).show();
                progress.dismiss();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //terminate
                Toast.makeText(getApplicationContext(), "Not Deleted", Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void display() {
        ypkgname.setText(pkg.getPkgname());
        ypkgid.setText(ypkgid.getText() + pkg.getPkgid());
        ypkgcost.setText(ypkgcost.getText() + pkg.getPkgcost() + " BDT / P");
        yduration.setText(yduration.getText() + pkg.getPkgduration());
        ytraveldate.setText(ytraveldate.getText() + pkg.getTraveldate());
        yminimumperson.setText(yminimumperson.getText() + pkg.getMinimumperson() + " minimum");
        yhotel.setText(yhotel.getText() + pkg.getHotel());


    }
}