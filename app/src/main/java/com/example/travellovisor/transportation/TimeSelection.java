package com.example.travellovisor.transportation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travellovisor.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TimeSelection extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private Button setdate;


    private FirebaseRecyclerOptions<Model> options;
    private FirebaseRecyclerAdapter<Model, MyViewHolder> adapter;

    RecyclerView recyclerView;

    private FirebaseDatabase database;
    //private DatabaseReference db;

    private DatabaseReference ref;
    private String location="";
    String time="";
    String date="";

    private TextView tv_from,tv_to,tv_time;
    private String from,to;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);

        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("id");

        if(from.equals(to)){
            onBackPressed();
            Toast.makeText(getApplicationContext(), "Please select other location", Toast.LENGTH_SHORT).show();
        }

        tv_from=findViewById(R.id.tv_from);
        tv_to=findViewById(R.id.tv_to);
        tv_time=findViewById(R.id.tv_time);
        tv_from.setText(from);
        tv_to.setText(to);


        //for the dates
        dateView =  findViewById(R.id.textView3);
        //setdate = findViewById(R.id.setdate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        //for showing the times in a list
        database = FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Transportation/Bus/Time/Time");

        recyclerView = findViewById(R.id.recyclerView_rv);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(ref, Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, MyViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {

                final String id1 = model.getId();
                final String name1 = model.getName();

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        date=dateView.getText().toString();
                        time=model.getId();
                        location=from+"/"+to+"/"+date+"/"+time;

                        tv_time.setText(time);



                    }
                });
                holder.textViewID.setText("" + model.getId());
                holder.textViewName.setText("" + model.getName());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);
                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);



    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        /*Toast.makeText(getApplicationContext(), "calender",
                Toast.LENGTH_SHORT)
                .show();*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,R.style.DialogThemeCal,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                     arg1 = year;
                     arg2 = month;
                     arg3 = day;
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("_")
                .append(month).append("_").append(year));
        date=dateView.getText().toString();
    }

    public void chooseSeatsActivity(View view) {
        if(tv_time.getText().equals("Please select time from below")){
            Toast.makeText(getApplicationContext(), "Please select a time first", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i=new Intent(getApplicationContext(), ChooseSeats.class);

            i.putExtra("location",location);
            i.putExtra("from",from);
            i.putExtra("to",to);
            i.putExtra("date",date);
            i.putExtra("time",time);
            startActivity(i);

        }

    }
}