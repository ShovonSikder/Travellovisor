package com.example.travellovisor.transportation;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travellovisor.BusTime;
import com.example.travellovisor.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusDestination extends AppCompatActivity {


    private FirebaseRecyclerOptions<Model> options;
    private FirebaseRecyclerAdapter<Model, MyViewHolder> adapter;

    RecyclerView recyclerView;

    private FirebaseDatabase database;
    //private DatabaseReference db;

    private DatabaseReference ref;
    private Toolbar ntoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_destinantion);


       /* ntoolbar= this.<Toolbar>findViewById(R.id.main_page_toolbar_IDadminPanel_main);
        setSupportActionBar(ntoolbar);
        getSupportActionBar().setTitle("Request's List");*/

        database = FirebaseDatabase.getInstance("https://travellovisor-829f1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Transportation/Bus/Routes");

        //ref= FirebaseDatabase.getInstance().getReference().child("Requests");
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
                        Intent intent = new Intent(getApplicationContext(), BusTime.class);
                        intent.putExtra("id", id1);
                        intent.putExtra("name", name1);
                        startActivity(intent);
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
}

