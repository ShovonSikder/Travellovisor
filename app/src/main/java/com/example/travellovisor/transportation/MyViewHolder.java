package com.example.travellovisor.transportation;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.travellovisor.R;

import java.text.BreakIterator;

public class MyViewHolder extends ViewHolder {
    public View view;

    public TextView textViewID;
    public TextView textViewName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewID = (TextView)itemView.findViewById(R.id.tv_1);
        textViewName = (TextView)itemView.findViewById(R.id.tv_2);
        view = itemView;
    }
}
