package com.example.travellovisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    Context cntx;
    ArrayList<SlideImage> imglinks;

    public SlideAdapter(Context cntx,ArrayList<SlideImage> imglinks) {
        this.cntx=cntx;
        this.imglinks = imglinks;
    }

    @Override
    public int getCount() {
        return imglinks.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.slide_simple_view,container,false);

        ImageView slideimg= view.findViewById(R.id.slideimg);

        Picasso.get().load(imglinks.get(position).getImglink()).into(slideimg);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
