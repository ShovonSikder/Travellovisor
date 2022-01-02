package com.example.travellovisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.account.LoginActivity;
import com.example.travellovisor.services.PackageTour;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UsersProfile extends AppCompatActivity {
    ImageView profileImg;
    TextView greet;
    LinearLayout yourpkg;
    FirebaseDatabase database;
    DatabaseReference db;
    FirebaseAuth nAuth;
    FirebaseUser nUser;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        //refer
        profileImg = findViewById(R.id.profileImg);
        greet = findViewById(R.id.greet);
        yourpkg = findViewById(R.id.yourpkg);

        nAuth = FirebaseAuth.getInstance();
        nUser = nAuth.getCurrentUser();

        if(nUser==null){
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("reqFrom","profile");
            startActivity(intent);
            finish();
        }

        //get database reference
        database = FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db = database.getReference("users").child(nUser.getUid());


        yourpkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), YourPackageList.class));
            }
        });

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading info...");
        progress.show();


        //retrieve data from database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //on update clear previous data and store new

                progress.show();
                user = snapshot.getValue(User.class);
                greet.setText("Hello " + user.getName());
                if (user.getImglink() != null)
                    Picasso.get().load(user.getImglink()).into(profileImg);
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }
}