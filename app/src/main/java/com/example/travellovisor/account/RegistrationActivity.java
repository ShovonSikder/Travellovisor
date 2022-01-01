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

import com.example.travellovisor.R;
import com.example.travellovisor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name,et_email,et_password;
    private TextView btn_register,tv_goto_login;
    private ProgressDialog progressDialog;

    FirebaseAuth nAuth;
    FirebaseUser nUser;
    FirebaseDatabase database;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

        btn_register=findViewById(R.id.tv_register);
        tv_goto_login=findViewById(R.id.tv_goto_login);

        progressDialog=new ProgressDialog(this);
        nAuth=FirebaseAuth.getInstance();
        nUser=nAuth.getCurrentUser();

        btn_register.setOnClickListener(this);
        tv_goto_login.setOnClickListener(this);

        //get database reference
        database=FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app");
        db=database.getReference("users");


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.tv_register){
            registerUser();
        }
        else if (view.getId()==R.id.tv_goto_login){
            sendToActivity();
        }
    }



    private void registerUser() {
        String name=et_name.getText().toString();
        String email=et_email.getText().toString();
        String password=et_password.getText().toString();

        progressDialog.setMessage("Please wait.....");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    db.child(nUser.getUid()).setValue(new User(name));
                    progressDialog.dismiss();
                    sendToActivity();
                    Toast.makeText(getApplicationContext(), "Registration Succesful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendToActivity() {
        Intent i=new Intent(this, LoginActivity.class);
        getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}