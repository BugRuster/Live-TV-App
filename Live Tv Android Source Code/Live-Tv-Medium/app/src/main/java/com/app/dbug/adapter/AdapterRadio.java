package com.app.dbug.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.dbug.activity.SplashActivity;
import com.app.dbug.model.Channel;
import com.bumptech.glide.Glide;
import com.app.dbug.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;

public class AdapterRadio extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> modelBase;
    public Context context;
    private OnItemClickListener mOnItemClickListener;
    SharedPreferences sharedPreferences;
    boolean isDark;
    ArrayList<Channel.Datum> channelList;
    int arryIndex;
    ArrayList<Channel.Datum> recentChannlView;


    public interface OnItemClickListener {
        void onItemClick(View view, Channel.Datum obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterRadio(Context context, List<Object> modelBase) {
        this.modelBase = modelBase;
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
    }

    public static class RadioHolder extends RecyclerView.ViewHolder {

        ImageView channelImageView;
        TextView channelViewTitle;
        TextView channelViewCategory;
        TextView textViewOptions;
        ImageView favoriteNullAdapter;
        ImageView favoriteFullAdapter;
//        CardView cardview;
        RelativeLayout itemCard;

        public RadioHolder(View view) {
            super(view);
            channelImageView = itemView.findViewById(R.id.channelImageView);
            channelViewTitle = itemView.findViewById(R.id.channelViewTitle);
            channelViewCategory = itemView.findViewById(R.id.channelViewCategory);
            favoriteNullAdapter = itemView.findViewById(R.id.favorite_null_adapter);
            favoriteFullAdapter = itemView.findViewById(R.id.favorite_full_adapter);
            textViewOptions = itemView.findViewById(R.id.textViewOptions);
//            cardview = itemView.findViewById(R.id.cardview);
            itemCard = itemView.findViewById(R.id.item_card);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channal_item_all_activity_glid, parent, false);
        vh = new RadioHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof RadioHolder) {
            final Channel.Datum c = (Channel.Datum) modelBase.get(position);

            final RadioHolder radioHolder = (RadioHolder) holder;
            int count = 0;
//            if (isDark) {
//                radioHolder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.tool_bar_color));
//            } else {
//                radioHolder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
//
//            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);

            radioHolder.channelViewTitle.setText(c.name);
            radioHolder.channelViewCategory.setText(c.cat_name);
            Glide.with(holder.itemView)
                    .load(ADMIN_PANEL_URL + IMAGE_URL + c.image)
                    .fitCenter()
                    .thumbnail(0.3f)
                    .into(radioHolder.channelImageView);
            radioHolder.itemView.setOnClickListener(view -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, c, position);
                    int idlist;
                    for (int i = 0; i < recentChannlView.size(); i++) {
                        if (recentChannlView.get(i).id == c.id) {
                            idlist = i;
                            recentChannlView.remove(idlist);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(recentChannlView);
                            editor.putString("recent", json);
                            editor.apply();

                        }
                    }


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(recentChannlView);
                    editor.putString("recent", json);
                    editor.apply();
                }
            });

            radioHolder.favoriteNullAdapter.setOnClickListener(v -> {
                    channelList.add(new Channel.Datum(c.id, c.cat_id, c.url_id, c.name, c.image, c.description, c.url, c.user_agent, c.token, c.extra, c.created_at, c.updated_at, c.cat_name, c.category_type));
                radioHolder.favoriteNullAdapter.setVisibility(View.GONE);
                radioHolder.favoriteFullAdapter.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(channelList);
                    editor.putString("tes", json);
                    editor.apply();

                });

            radioHolder.favoriteFullAdapter.setOnClickListener(v -> {
                radioHolder.favoriteNullAdapter.setVisibility(View.VISIBLE);
                radioHolder.favoriteFullAdapter.setVisibility(View.GONE);
                    boolean itsCurrentFavoriteID = true;
                    int idlist = 0;
                    while (itsCurrentFavoriteID) {
                        for (int i = 0; i < channelList.size(); i++) {
                            if (channelList.get(i).id == c.id) {
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
                    radioHolder.favoriteNullAdapter.setVisibility(View.VISIBLE);
                    radioHolder.favoriteFullAdapter.setVisibility(View.GONE);

                } else {
                    for (int i = 0; i < channelList.size(); i++) {
                        if (channelList.get(i).id == c.id) {
                            arryIndex = count;

                            radioHolder.favoriteNullAdapter.setVisibility(View.GONE);
                            radioHolder.favoriteFullAdapter.setVisibility(View.VISIBLE);
                        }
                        count = count + 1;

                    }

                }

                if (c.category_type == 0) {
                    radioHolder.favoriteNullAdapter.setVisibility(View.GONE);
                    radioHolder.favoriteFullAdapter.setVisibility(View.GONE);
                }


        }
    }

    @Override
    public int getItemCount() {
        return modelBase.size();
    }

}