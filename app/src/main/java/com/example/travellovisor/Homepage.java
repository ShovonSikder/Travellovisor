package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.travellovisor.account.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {
    private Button btn_logout;
    FirebaseAuth nAuth;
    FirebaseUser nUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        nAuth=FirebaseAuth.getInstance();
        nUser=nAuth.getCurrentUser();

        btn_logout=findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //refresh activity without blinking
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(nUser==null){
            goToLoginActivity();
        }
    }


    private void goToLoginActivity() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}