package com.app.dbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.app.dbug.R;
import com.app.dbug.activity.RadioActivity;
import com.app.dbug.activity.TvViewActivity;
import com.app.dbug.activity.WebViewActivity;
import com.app.dbug.api.ApiInter;
import com.app.dbug.model.Channel;
import com.app.dbug.model.SliderHome;
import com.app.dbug.retofit.RetrofitClient;
import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderAdapter2 extends RecyclerView.Adapter<SliderAdapter2.SliderViewHolder> {

    private List<SliderHome> sliderItems;
    private ViewPager2 viewPager2;

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

    public SliderAdapter2(List<SliderHome> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent,false);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderHome sliderItem = sliderItems.get(position);
        Log.d("fdsfsd", "onBindViewHolder: "+sliderItem.getFullImageUrl());

//        Glide.with(viewHolder.itemView)
//                .load(sliderItem.getFullImageUrl())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
        Picasso.get().load(sliderItem.fullImageUrl).into(holder.imageViewBackground);
        holder.imageViewBackground.setOnClickListener(v -> dataLoad(v.getContext(),sliderItem.pro_id+""));
        if(position == sliderItems.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewBackground;
        MaterialRippleLayout item;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.banner);
//            item =  itemView.findViewById(R.id.item);
        }
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
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
