package com.example.travellovisor.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.travellovisor.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class GuideListAdapter extends BaseAdapter {
    ArrayList<String> titles;
    ArrayList<String> descriptions;
    Context cntx;
    GuideListAdapter(Context cntx,ArrayList<String> titles,ArrayList<String> descriptions ){
        this.titles=titles;
        this.descriptions=descriptions;
        this.cntx=cntx;
    }
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.ttravel_guide_list_sample_view,parent,false);
        }
        TextView title=convertView.findViewById(R.id.title);
        TextView descip=convertView.findViewById(R.id.descrip);

        title.setText(titles.get(position));
        descip.setText(descriptions.get(position));

        return convertView;
    }
}
