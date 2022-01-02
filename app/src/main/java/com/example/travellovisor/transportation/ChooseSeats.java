package com.example.travellovisor.transportation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travellovisor.Payment;
import com.example.travellovisor.R;

import java.sql.Array;

public class ChooseSeats extends AppCompatActivity implements View.OnClickListener {
    String location;
    private ImageView a1,a2,a3,a4,b4,j1,j2,j3,j4;
    int[] seats = new int[41];
    private String from,to,date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seats);

        location=getIntent().getStringExtra("location");
        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("to");
        date=getIntent().getStringExtra("date");
        time=getIntent().getStringExtra("time");
        Toast.makeText(getApplicationContext(), location, Toast.LENGTH_SHORT).show();

        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);
        b4=findViewById(R.id.b4);
        j1=findViewById(R.id.j1);
        j2=findViewById(R.id.j2);
        j3=findViewById(R.id.j3);
        j4=findViewById(R.id.j4);

        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        b4.setOnClickListener(this);
        j1.setOnClickListener(this);
        j2.setOnClickListener(this);
        j3.setOnClickListener(this);
        j4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.a1){
            if(seats[1]!=1){
                seats[1]=1;
                a1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[1]=0;
                a1.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.a2){
            if(seats[2]!=1){
                seats[2]=1;
                a2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[2]=0;
                a2.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.a3){
            if(seats[3]!=1){
                seats[3]=1;
                a3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[3]=0;
                a3.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.a4){
            if(seats[4]!=1){
                seats[4]=1;
                a4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[4]=0;
                a4.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.b4){
            if(seats[8]!=1){
                seats[8]=1;
                b4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[8]=0;
                b4.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.j1)
        {
            if(seats[37]!=1){
                seats[37]=1;
                j1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[37]=0;
                j1.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.j2)
        {
            if(seats[38]!=1){
                seats[38]=1;
                j2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[38]=0;
                j2.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(view.getId()==R.id.j3)
        {
            if(seats[39]!=1){
                seats[39]=1;
                j3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[39]=0;
                j3.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }

        if(view.getId()==R.id.j4)
        {
            if(seats[40]!=1){
                seats[40]=1;
                j4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else{
                seats[40]=0;
                j4.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }

    }

    public void goToPayment(View view) {
        Intent intent=new Intent(getApplicationContext(), Payment.class);
        intent.putExtra("location",location);
        intent.putExtra("location",location);
        intent.putExtra("from",from);
        intent.putExtra("to",to);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        startActivity(intent);
    }
}