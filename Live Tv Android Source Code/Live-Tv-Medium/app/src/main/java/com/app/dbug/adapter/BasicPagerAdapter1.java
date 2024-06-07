package com.app.dbug.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.dbug.R;
import com.app.dbug.model.SliderHome;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BasicPagerAdapter1 extends PagerAdapter {

    List<SliderHome> covers;
    LayoutInflater layoutInflater;
    Context context;


    public BasicPagerAdapter1(Context context, List<SliderHome> sliderHomes) {
        this.context = context;
        covers = sliderHomes;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpager_item, null);

        ImageView iv = layout.findViewById(R.id.banner);
        SliderHome sliderHome = covers.get(position);
        Glide.with(container.getContext())
                .load(sliderHome.fullImageUrl)
                .fitCenter()
                .into(iv);
        Log.d("dfsdfs", "instantiateItem: " + sliderHome.fullImageUrl);

        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return covers.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
