package com.app.dbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dbug.R;
import com.app.dbug.activity.RadioActivity;
import com.app.dbug.activity.TvViewActivity;
import com.app.dbug.activity.WebViewActivity;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Channel;
import com.app.dbug.model.SliderHome;
import com.app.dbug.model.SliderItem;
import com.app.dbug.retofit.RetrofitClient;
import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private List<SliderHome> mSliderItems = new ArrayList<>();
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
    public void renewItems(List<SliderHome> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderHome sliderItem = mSliderItems.get(position);
        Log.d("fdsfsd", "onBindViewHolder: "+sliderItem.getFullImageUrl());

//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getFullImageUrl())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
        Picasso.get().load(sliderItem.fullImageUrl).into(viewHolder.imageViewBackground);
        viewHolder.imageViewBackground.setOnClickListener(v -> dataLoad(v.getContext(),sliderItem.pro_id+""));
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends ViewHolder {

        ImageView imageViewBackground;
        MaterialRippleLayout item;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.banner);
//            item =  itemView.findViewById(R.id.item);
        }
    }

    private void dataLoad(Context context , String sId) {
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
