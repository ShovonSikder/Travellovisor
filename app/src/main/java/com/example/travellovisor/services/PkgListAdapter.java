package com.example.travellovisor.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.travellovisor.R;

import java.util.ArrayList;

public class PkgListAdapter extends BaseAdapter {

    ArrayList<PackageTour> pkges;
    Context cntx;

    public PkgListAdapter(Context cntx,ArrayList<PackageTour> pkges) {
        this.pkges = pkges;
        this.cntx=cntx;
    }

    @Override
    public int getCount() {
        return pkges.size();
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
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.ttravel_guide_list_sample_view,parent,false);
        }
        TextView title=convertView.findViewById(R.id.title);
        TextView descrip=convertView.findViewById(R.id.descrip);

        title.setText(pkges.get(position).getPkgname());
        descrip.setText("Cost: "+pkges.get(position).getPkgcost()+"/ person | @"+pkges.get(position).getHotel()+" | Date: "+pkges.get(position).getTraveldate());

        return convertView;
    }
}
