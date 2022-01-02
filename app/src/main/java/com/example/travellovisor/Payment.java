package com.example.travellovisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseUser currentFirebaseUser;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference,ref;
    String location;
    private String from,to,date,time;
    private Button bookseatsDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        bookseatsDone=findViewById(R.id.bookseatsDone);


        location=getIntent().getStringExtra("location");
        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("to");
        date=getIntent().getStringExtra("date");
        time=getIntent().getStringExtra("time");


        location=getIntent().getStringExtra("location");

         currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        database = FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = database.getReference("Booking"+"/"+location);
        ref=database.getReference("Bus/"+String.valueOf(currentFirebaseUser));


        bookseatsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookSeats();
            }
        });

    }

    public void bookSeats() {
        databaseReference.child(String.valueOf(currentFirebaseUser)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(String.valueOf(currentFirebaseUser)).setValue("a1");
                Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();
                ref.child("From").setValue(from);
                ref.child("to").setValue(to);
                ref.child("date").setValue(date);
                ref.child("time").setValue(time);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}