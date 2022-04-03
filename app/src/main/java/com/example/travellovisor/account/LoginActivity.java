package com.example.travellovisor.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.example.travellovisor.UsersProfile;
import com.example.travellovisor.services.TourPackageDetails;
import com.example.travellovisor.services.TravelGuide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_password;
    private TextView tv_login, tv_goto_register;
    private ProgressDialog progressDialog;

    FirebaseAuth nAuth;
    FirebaseUser nUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        tv_login = findViewById(R.id.tv_login);
        tv_goto_register = findViewById(R.id.tv_goto_register);

        progressDialog = new ProgressDialog(this);
        nAuth = FirebaseAuth.getInstance();
        nUser = nAuth.getCurrentUser();

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tv_goto_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegisterActivity();
            }
        });
    }

    private void gotoRegisterActivity() {
        Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(i);
        finish();
    }

    private void login() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(),"Email & Pass must required",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Please wait.....");
        progressDialog.setTitle("Loging in");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        nAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                   try {
                       sendToActivity(getIntent().getStringExtra("reqFrom"));
                   }catch (Exception e){
                       sendToActivity("");
                   }
                   finally {
                       finish();
                   }
                    Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void sendToActivity(String reqFrom) {

        if (reqFrom.equals("TPD")) {
            Intent i = new Intent(this, TourPackageDetails.class);
            getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else if (reqFrom.equals("profile")) {
            Intent i = new Intent(this, UsersProfile.class);
            getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else {
            Intent i = new Intent(this, Homepage.class);
            getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}