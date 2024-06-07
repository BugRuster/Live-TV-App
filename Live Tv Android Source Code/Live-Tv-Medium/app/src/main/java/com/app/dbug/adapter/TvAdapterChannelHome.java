package com.app.dbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dbug.activity.RadioActivity;
import com.app.dbug.activity.SplashActivity;
import com.app.dbug.activity.TvViewActivity;
import com.app.dbug.activity.WebViewActivity;
import com.app.dbug.utils.ItemAnimation;
import com.bumptech.glide.Glide;
import com.app.dbug.R;
import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.model.Channel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;

public class TvAdapterChannelHome extends RecyclerView.Adapter<TvAdapterChannelHome.ChannelView> implements InterAdClickInterFace{

    List<Channel.Datum> itemModelsList;
    Context context;
    SessionAds sessionAds;

    ArrayList<Channel.Datum> channelList;
    ArrayList<Channel.Datum> recentChannlView;
    int arryIndex;
    SharedPreferences sharedPreferences;
    boolean isDark;

    private int lastPosition = -1;
    private boolean on_attach = true;
    private int animation_type = 2;

    public TvAdapterChannelHome(Context context) {
        this.context = context;
    }

    public TvAdapterChannelHome(Context context , List<Channel.Datum> itemModelsList) {
        this.itemModelsList = itemModelsList;
        this.context = context;
        this.sessionAds =new SessionAds(context,this);
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChannelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item.xml using LayoutInflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.channelitem_home, parent, false);
        // return itemView
        return new ChannelView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelView holder, int position) {
        final  Channel.Datum channelItem = itemModelsList.get(position);

        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        if (isDark) {
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.maincolor));
        } else {

            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }
        holder.channelViewTitle.setText(channelItem.name);
        holder.channelViewCategory.setText(channelItem.cat_name);
        Glide.with(holder.itemView)
                .load(ADMIN_PANEL_URL+ IMAGE_URL +channelItem.image)
                .fitCenter()

                .thumbnail(0.3f)
                .into(holder.ChannelImageView);

        holder.itemView.setOnClickListener(v -> {

            if (channelItem.url_id == 9){
                Intent intent = new Intent(v.getContext(), RadioActivity.class);
                intent.putExtra("id", channelItem.id + "");
                intent.putExtra("cat_id", channelItem.cat_id + "");
                intent.putExtra("Title", channelItem.name);
                intent.putExtra("Full", channelItem.description);
                intent.putExtra("Image", channelItem.image);
                intent.putExtra("created_at", channelItem.created_at);
                intent.putExtra("updated_at", channelItem.updated_at);
                intent.putExtra("Category", "test");
                intent.putExtra("tv_url", channelItem.url);
                intent.putExtra("user_agent", channelItem.user_agent);
                intent.putExtra("token", channelItem.token);
                intent.putExtra("extra", channelItem.extra);
                intent.putExtra("created_at", channelItem.created_at + "");
                intent.putExtra("updated_at", channelItem.updated_at + "");
                intent.putExtra("url_id", channelItem.url_id + "");
                v.getContext().startActivity(intent);
                new AnimIntent.Builder(v.getContext()).performSlideToLeft();
            }else if (channelItem.url_id == 10){
                Intent intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("id", channelItem.id + "");
                intent.putExtra("cat_id", channelItem.cat_id + "");
                intent.putExtra("Title", channelItem.name);
                intent.putExtra("Full", channelItem.description);
                intent.putExtra("Image", channelItem.image);
                intent.putExtra("created_at", channelItem.created_at);
                intent.putExtra("updated_at", channelItem.updated_at);
                intent.putExtra("Category", "test");
                intent.putExtra("tv_url", channelItem.url);
                intent.putExtra("user_agent", channelItem.user_agent);
                intent.putExtra("token", channelItem.token);
                intent.putExtra("extra", channelItem.extra);
                intent.putExtra("created_at", channelItem.created_at + "");
                intent.putExtra("updated_at", channelItem.updated_at + "");
                intent.putExtra("url_id", channelItem.url_id + "");
                v.getContext().startActivity(intent);
                new AnimIntent.Builder(v.getContext()).performSlideToLeft();
            } else {
                Intent intent = new Intent(v.getContext(), TvViewActivity.class);
                intent.putExtra("id", channelItem.id + "");
                intent.putExtra("cat_id", channelItem.cat_id + "");
                intent.putExtra("Title", channelItem.name);
                intent.putExtra("Full", channelItem.description);
                intent.putExtra("Image", channelItem.image);
                intent.putExtra("created_at", channelItem.created_at);
                intent.putExtra("updated_at", channelItem.updated_at);
                intent.putExtra("Category", "test");
                intent.putExtra("tv_url", channelItem.url);
                intent.putExtra("user_agent", channelItem.user_agent);
                intent.putExtra("token", channelItem.token);
                intent.putExtra("extra", channelItem.extra);
                intent.putExtra("created_at", channelItem.created_at + "");
                intent.putExtra("updated_at", channelItem.updated_at + "");
                intent.putExtra("url_id", channelItem.url_id + "");
                v.getContext().startActivity(intent);
                new AnimIntent.Builder(v.getContext()).performSlideToLeft();
            }
            //sessionAds.showInter();
        });

        holder.favoriteNullAdapter.setOnClickListener(v -> {
            channelList.add(new Channel.Datum(channelItem.id,channelItem.cat_id,channelItem.url_id,channelItem.name,channelItem.image,channelItem.description,channelItem.url,channelItem.user_agent,channelItem.token,channelItem.extra,channelItem.created_at,channelItem.updated_at,channelItem.cat_name,channelItem.category_type));
            holder.favoriteNullAdapter.setVisibility(View.GONE);
            holder.favoriteFullAdapter.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(channelList);
            editor.putString("tes", json);
            editor.apply();

        });

        holder.favoriteFullAdapter.setOnClickListener(v -> {
            holder.favoriteNullAdapter.setVisibility(View.VISIBLE);
            holder.favoriteFullAdapter.setVisibility(View.GONE);
            boolean itsCurrentFavoriteID = true;
            int idlist = 0;
            while (itsCurrentFavoriteID) {
                for (int i = 0; i < channelList.size(); i++) {
                    if (channelList.get(i).id == channelItem.id) {
                        idlist = i;
                        itsCurrentFavoriteID = false;
                    }

                }

            }
            if (channelList.size() == 1) {
                channelList.remove(0);
            } else {
                channelList.remove(idlist);
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(channelList);
            editor.putString("tes", json);
            editor.apply();

        });


        int count = 0;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tes", null);
        String jsonrecent = sharedPreferences.getString("recent", null);
        Type type = new TypeToken<ArrayList<Channel.Datum>>() {
        }.getType();
        Type typerecent = new TypeToken<ArrayList<Channel.Datum>>() {
        }.getType();

        channelList = gson.fromJson(json, type);
        recentChannlView = gson.fromJson(jsonrecent, typerecent);

        if (recentChannlView == null) {
            recentChannlView = new ArrayList<>();
        } else {
            //Empty
        }
        if (channelList == null) {
            channelList = new ArrayList<>();
            holder.favoriteNullAdapter.setVisibility(View.VISIBLE);
            holder.favoriteFullAdapter.setVisibility(View.GONE);

        } else {
            for (int i = 0; i < channelList.size(); i++) {
                if (channelList.get(i).id == channelItem.id) {
                    arryIndex = count;

                    holder.favoriteNullAdapter.setVisibility(View.GONE);
                    holder.favoriteFullAdapter.setVisibility(View.VISIBLE);
                }
                count = count + 1;

            }

        }

        if (channelItem.category_type == 0) {
            holder.favoriteNullAdapter.setVisibility(View.GONE);
            holder.favoriteFullAdapter.setVisibility(View.GONE);
        }

        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View view, int position) {

        Log.d("sdjfhjksd", "setAnimation: "+position);
        if (position > lastPosition) {

            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return itemModelsList.size();
    }

    @Override
    public void onAdClick() {
        Log.d("TAG", "onAdClick: ");
    }

    @Override
    public void onAdFailed() {
        Log.d("TAG", "onAdFailed: ");
    }

    public class ChannelView extends RecyclerView.ViewHolder {
        CardView cardview;
        ImageView ChannelImageView;
        TextView channelViewTitle;
        TextView channelViewCategory;
        ImageView favoriteNullAdapter;
        ImageView favoriteFullAdapter;
        public ChannelView(@NonNull View itemView) {
            super(itemView);
            ChannelImageView = itemView.findViewById(R.id.channelImageView);
            channelViewTitle = itemView.findViewById(R.id.channelViewTitle);
            channelViewCategory = itemView.findViewById(R.id.channelViewCategory);
            favoriteNullAdapter = itemView.findViewById(R.id.favorite_null_adapter);
            favoriteFullAdapter = itemView.findViewById(R.id.favorite_full_adapter);
            cardview = itemView.findViewById(R.id.cardview);

        }
    }
}
