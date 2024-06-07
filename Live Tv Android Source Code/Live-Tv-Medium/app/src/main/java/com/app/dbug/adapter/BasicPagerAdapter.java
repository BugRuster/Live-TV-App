package com.app.dbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.dbug.R;
import com.app.dbug.activity.RadioActivity;
import com.app.dbug.activity.TvViewActivity;
import com.app.dbug.activity.WebViewActivity;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Channel;
import com.app.dbug.model.SliderHome;
import com.app.dbug.retofit.RetrofitClient;
import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.appnext.base.b.b.getContext;

public class BasicPagerAdapter extends PagerAdapter {

    private final List<SliderHome> covers;
    private final LayoutInflater layoutInflater;
    Context context;

    SessionAds sessionAds;
    String imageString;
    String titleString;
    String categoryString;
    String fullString;
    String id1;
    String catId;
    String createdAt;
    String updatedAt;
    String videoUrl1;
    String user_agent;
    String token;
    String url_id;
    public BasicPagerAdapter(Context context,List<SliderHome> sliderHomes) {
        this.context = context;
        covers = sliderHomes;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public  Object  instantiateItem (@NonNull ViewGroup container , int  position ) {
        View view = layoutInflater.inflate(R.layout.viewpager_item, container, false);
        ImageView iv = view.findViewById(R.id.banner);
//        MaterialRippleLayout item =  view.findViewById(R.id.item);
        SliderHome sliderHome = covers.get(position);
        Glide.with(container.getContext())
                .load(sliderHome.fullImageUrl)
                .fitCenter()
                .into(iv);
//        Picasso.get().load(sliderHome.fullImageUrl).into(iv);
        container.addView(view);


        iv.setOnClickListener(v -> dataLoad(v.getContext(),sliderHome.pro_id+""));

        return view;
    }
    @Override
    public int getCount() {
        return covers.size();
    }

    @Override
    public boolean isViewFromObject (@NonNull  View  view , @NonNull  Object  object ) {
        return view == object;
    }

    @Override
    public  void  destroyItem ( @NonNull  ViewGroup  container , int  position , @NonNull  Object  object ) {
        container.removeView((View) object);
    }

    private void dataLoad(Context context ,String sId) {
        ApiInter apiInter = RetrofitClient.getRetrofit().create(ApiInter.class);
        apiInter.getSingleChannelBody(sId)
                .enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        if (response.isSuccessful()) {
                            Channel channel = response.body();
                            List<Channel.Datum> post = channel.data;

                            if (!post.isEmpty()) {
                                id1 = post.get(0).id + "";
                                catId = post.get(0).cat_id + "";
                                createdAt = post.get(0).created_at + "";
                                updatedAt = post.get(0).updated_at + "";
                                imageString = post.get(0).image;
                                titleString = post.get(0).name;
                                categoryString = post.get(0).cat_name;
                                fullString = post.get(0).description;
                                user_agent = post.get(0).user_agent;
                                token = post.get(0).token;
                                videoUrl1 = post.get(0).url;
                                url_id = post.get(0).url_id + "";



                                if (url_id.equals("9")){
                                    Intent intent = new Intent(context, RadioActivity.class);
                                    intent.putExtra("id", id1 + "");
                                    intent.putExtra("cat_id", catId + "");
                                    intent.putExtra("Title", titleString);
                                    intent.putExtra("Full", fullString);
                                    intent.putExtra("Image", imageString);
                                    intent.putExtra("created_at", createdAt);
                                    intent.putExtra("updated_at", updatedAt);
                                    intent.putExtra("Category", "test");
                                    intent.putExtra("tv_url", videoUrl1);
                                    intent.putExtra("user_agent", user_agent);
                                    intent.putExtra("token", token);
                                    intent.putExtra("extra", "");
                                    intent.putExtra("url_id", url_id + "");
                                    context.startActivity(intent);
                                    new AnimIntent.Builder(context).performSlideToLeft();

                                }else if (url_id.equals("10")){
                                    Intent intent = new Intent(context, WebViewActivity.class);
                                    intent.putExtra("id", id1 + "");
                                    intent.putExtra("cat_id", catId + "");
                                    intent.putExtra("Title", titleString);
                                    intent.putExtra("Full", fullString);
                                    intent.putExtra("Image", imageString);
                                    intent.putExtra("created_at", createdAt);
                                    intent.putExtra("updated_at", updatedAt);
                                    intent.putExtra("Category", "test");
                                    intent.putExtra("tv_url", videoUrl1);
                                    intent.putExtra("user_agent", user_agent);
                                    intent.putExtra("token", token);
                                    intent.putExtra("extra", "");
                                    intent.putExtra("url_id", url_id + "");
                                    context.startActivity(intent);
                                    new AnimIntent.Builder(context).performSlideToLeft();
                                } else {
                                    Intent intent = new Intent(context, TvViewActivity.class);
                                    intent.putExtra("id", id1 + "");
                                    intent.putExtra("cat_id", catId + "");
                                    intent.putExtra("Title", titleString);
                                    intent.putExtra("Full", fullString);
                                    intent.putExtra("Image", imageString);
                                    intent.putExtra("created_at", createdAt);
                                    intent.putExtra("updated_at", updatedAt);
                                    intent.putExtra("Category", "test");
                                    intent.putExtra("tv_url", videoUrl1);
                                    intent.putExtra("user_agent", user_agent);
                                    intent.putExtra("token", token);
                                    intent.putExtra("extra", "");
                                    intent.putExtra("url_id", url_id + "");
                                    context.startActivity(intent);
                                    new AnimIntent.Builder(context).performSlideToLeft();
                                }

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {
                        Log.d("mmmmm", t.getMessage());
                    }
                });
    }
}
