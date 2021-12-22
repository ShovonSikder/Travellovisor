package com.example.travellovisor.account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPsendActivity extends AppCompatActivity {

    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private EditText edtPhone, edtOTP;

    // buttons for generating OTP and verifying OTP
    private Button verifyOTPBtn, generateOTPBtn;

    // string for storing our verification ID
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsend);

        // below line is for getting instance
        // of our FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        // initializing variables for button and Edittext.
        edtPhone = findViewById(R.id.idEdtPhoneNumber);
        edtOTP = findViewById(R.id.idEdtOtp);
        verifyOTPBtn = findViewById(R.id.idBtnVerify);
        generateOTPBtn = findViewById(R.id.idBtnGetOtp);

        // setting onclick listener for generate OTP button.
        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(OTPsendActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+88" + edtPhone.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });

        // initializing on click listener
        // for verify otp button
        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(OTPsendActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(edtOTP.getText().toString());
                }
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(OTPsendActivity.this, Homepage.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(OTPsendActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(OTPsendActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}







/*
package com.example.travellovisor.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.travellovisor.Homepage;
import com.example.travellovisor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OTPsendActivity extends AppCompatActivity {
    private EditText nphonenumber,ncode;
    private Button nsendBtn,verifybtn;
    private ProgressBar progressBar;

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks ncallbacks;

    String nVerificationID;

    */
/**this is for UserDatabase*//*

    private DatabaseReference rootREF;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsend);
        FirebaseApp.initializeApp(this);

        //checks user logged in or not
        userLoggedIn();

        nphonenumber= this.<EditText>findViewById(R.id.et_phone_ID);
        ncode= this.<EditText>findViewById(R.id.et_verification_code_id);
        nsendBtn= this.<Button>findViewById(R.id.btn_send_verify_id);
        //progressBar= this.<ProgressBar>findViewById(R.id.progressbar_id);

        ncode.setVisibility(View.GONE);

        */
/**this is for UserDatabase*//*

        rootREF= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();



        //progressBar.setVisibility(View.GONE);

        nsendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nVerificationID != null){
                    //progressBar.setVisibility(View.VISIBLE);
                    verifyphoneNumberwithcode();
                }
                else {
                    //progressBar.setVisibility(View.VISIBLE);
                    startverificationofphonenumber();
                }

            }
        });

        ncallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String VerificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                super.onCodeSent(VerificationID, forceResendingToken);
                nVerificationID=VerificationID;
                ncode.setVisibility(View.VISIBLE);
                nsendBtn.setText("Verify Code");
                //progressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    private void verifyphoneNumberwithcode(){

        if(TextUtils.isEmpty(ncode.getText().toString())){
            Toast.makeText(this, "please enter code", Toast.LENGTH_SHORT).show();
        }
        else{
            PhoneAuthCredential credential=PhoneAuthProvider.getCredential(nVerificationID,ncode.getText().toString());
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //problem here
                    //
                    //
                    //
                    //
                    //problem here for mthat reasoon not login
                    final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user !=null){

                       */
/* final DatabaseReference muserdb= FirebaseDatabase.getInstance().getReference().child(user.getUid());
                        muserdb.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(!dataSnapshot.exists()){
                                    Map<String ,Object> usermap=new HashMap<>();
                                    usermap.put("phone",user.getPhoneNumber());
                                    usermap.put("name",user.getPhoneNumber());
                                    muserdb.updateChildren(usermap);
                                }*//*


                        userLoggedIn();

                         */
/*   }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });*//*


                    }

                }
            }
        });

    }

    private void userLoggedIn() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){

            */
/**this is for UserDatabase*//*

            */
/**this is for creating user in Database*//*

            createuserInDatabase();

            */
/**Main2 activity*//*


            sendToMainActivity();
            */
/*startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            finish();
            return;*//*

        }

    }

    private void createuserInDatabase() {
        final String currentUser_ID_in_database=mAuth.getCurrentUser().getUid();
        rootREF.child("Users").child(currentUser_ID_in_database)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            //
                        }
                        else
                        {
                            rootREF.child("Users").child(currentUser_ID_in_database).setValue("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void startverificationofphonenumber() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+88"+nphonenumber.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(ncallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void sendToMainActivity() {
        Intent main_intent=new Intent(getApplicationContext(), Homepage.class);
        main_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main_intent);
        finish();
    }
}*/
