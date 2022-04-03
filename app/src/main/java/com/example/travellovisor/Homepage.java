package com.example.travellovisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travellovisor.account.LoginActivity;
import com.example.travellovisor.services.FindPlaces;
import com.example.travellovisor.services.TourPackages;
import com.example.travellovisor.services.TravelGuide;
import com.example.travellovisor.transportation.Bus_Source;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import barikoi.barikoilocation.BarikoiAPI;
import barikoi.barikoilocation.PlaceModels.GeoCodePlace;
import barikoi.barikoilocation.SearchAutoComplete.SearchAutocompleteFragment;

public class Homepage extends AppCompatActivity {
    private Button btn_logout;
    FirebaseAuth nAuth;
    FirebaseUser nUser;

    ImageView clndrIcon,searchNow;
    EditText editDate;

    SearchAutocompleteFragment searchAutocompleteFragmentSRC,searchAutocompleteFragmentDEST;

    String src="",dest="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        nAuth=FirebaseAuth.getInstance();
        nUser=nAuth.getCurrentUser();

        btn_logout=findViewById(R.id.btn_logout);
        clndrIcon=findViewById(R.id.clndrIcon);
        editDate=findViewById(R.id.editDate);
        searchNow=findViewById(R.id.searchNow);


        //barikoi API used here of auto search complete
        BarikoiAPI.getINSTANCE(getApplicationContext(),"MzE5MTozWFlFWkxPRVhZ");


        searchAutocompleteFragmentSRC=(SearchAutocompleteFragment)getSupportFragmentManager().findFragmentById(R.id.barikoiSearchAutocompleteFragmentSRC);
        searchAutocompleteFragmentDEST=(SearchAutocompleteFragment)getSupportFragmentManager().findFragmentById(R.id.barikoiSearchAutocompleteFragmentDEST);



        searchAutocompleteFragmentSRC.setPlaceSelectionListener(new SearchAutocompleteFragment.PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(GeoCodePlace place) {
                src= place.getAddress();
                Toast.makeText(getApplicationContext(), "Place Selected: "+place.getAddress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), "Error Message"+error, Toast.LENGTH_SHORT).show();
            }

        });

        searchAutocompleteFragmentDEST.setPlaceSelectionListener(new SearchAutocompleteFragment.PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(GeoCodePlace place) {
                dest= place.getAddress();
                Toast.makeText(getApplicationContext(), "Place Selected: "+place.getAddress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), "Error Message"+error, Toast.LENGTH_SHORT).show();
            }
        });
        //barikoi ends


        //click on calender icon to choose from calender picker
        clndrIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment datePickerFragment=new DatePickerFragment(editDate,false);
                datePickerFragment.show(getSupportFragmentManager(),"datepickercal");

            }
        });

        //click on edit text for spinner picker
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment datePickerFragment=new DatePickerFragment(editDate,true);
                datePickerFragment.show(getSupportFragmentManager(),"datepickerspin");

            }
        });

        searchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSearchPackage();
            }
        });

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


    public void goToFindPlac(View view) {
        Intent intent=new Intent(getApplicationContext(), FindPlaces.class);
        startActivity(intent);
    }

    public void findTransportation(View view) {
        Intent intent=new Intent(getApplicationContext(), Bus_Source.class);
        startActivity(intent);
    }

    public void travelGuide(View view) {
        Intent intent=new Intent(getApplicationContext(), TravelGuide.class);
        startActivity(intent);
    }

    public void tourPackage(View view) {
        Intent intent=new Intent(getApplicationContext(), TourPackages.class);
        startActivity(intent);
    }
    public void gotoSearchPackage(){
        if(src.equals("")||dest.equals("")||editDate.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "All field must required", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(getApplicationContext(),SearchNow.class);
        intent.putExtra("src",src);
        intent.putExtra("dest",dest);
        intent.putExtra("date",editDate.getText().toString());
        startActivity(intent);

        editDate.setText(null);

    }
}