package com.app.dbug.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dbug.R;
import com.app.dbug.activity.RadioActivity;
import com.app.dbug.activity.SplashActivity;
import com.app.dbug.activity.TvViewActivity;
import com.app.dbug.activity.WebViewActivity;
import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.model.Channel;
import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;

public class AdapterChannelListRe extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements InterAdClickInterFace {

    private final List<Object> modelBase;


    Context context;
    SessionAds sessionAds;

    ArrayList<Channel.Datum> channelList;
    ArrayList<Channel.Datum> recentChannlView;
    int arryIndex;
    SharedPreferences sharedPreferences;
    boolean fbNative = true;
    boolean isDark;

    public AdapterChannelListRe(Context context, List<Object> modelBase) {
        this.modelBase = modelBase;
        this.context = context;
        this.sessionAds = new SessionAds(context, this);
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case 1:
                if (fbNative) {
                    View unifiedNativeLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admob_native_ads, viewGroup, false);
                    return new UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
                } else {
                    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fb_native_ads_row, viewGroup, false);
                    return new NativeAdViewHolder(v);
                }

            case 0:
                //Not really needed code here as we have the default.
            default:
                View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channal_item_related, viewGroup, false);
                return new MyListHolder(menuItemLayoutView);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:
                if (fbNative) {

                    AdapterChannelList.UnifiedNativeAdViewHolder holder = (AdapterChannelList.UnifiedNativeAdViewHolder) holder1;
                    UnifiedNativeAd nativeAd = (UnifiedNativeAd) modelBase.get(position);
                    if (isDark) {
                        holder.cardAdmobColor.setCardBackgroundColor(ContextCompat.getColor(context, R.color.tool_bar_color));
                    } else {
                        holder.cardAdmobColor.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    }

                    Log.d("sdsad", "onBindViewHolder: "+nativeAd.getImages().get(0).getUri());
                    Glide.with(holder.itemView)
                            .load(nativeAd.getImages().get(0).getUri())
                            .fitCenter()

                            .thumbnail(0.3f)
                            .into(holder.ad_icon);



                    holder.ad_headline.setText(nativeAd.getHeadline());
                    holder.ad_body.setText(nativeAd.getBody());
                    holder.ad_price.setText(nativeAd.getPrice());
                    holder.ad_advertiser.setText(nativeAd.getAdvertiser());
                    holder.ad_store.setText(nativeAd.getStore());
                    double ree = nativeAd.getStarRating();
                    float f = (float)ree;
                    holder.ad_stars.setRating(f);
                } else {
                    NativeAdViewHolder viewHolder = (NativeAdViewHolder) holder1;

                    if (isDark) {
                        viewHolder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.tool_bar_color));
                    } else {
                        viewHolder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    }

                    com.facebook.ads.NativeAd ad = (com.facebook.ads.NativeAd) modelBase.get(position);
                    NativeAdBase.Image icon = ad.getAdIcon();
                    Glide.with(viewHolder.itemView)
                            .load(icon.getUrl())
                            .fitCenter()

                            .thumbnail(0.3f)
                            .into(viewHolder.testfbIcon);
                    viewHolder.nativeAdTitle.setText(ad.getAdHeadline());
                    viewHolder.nativeAdBody.setText(ad.getAdBodyText());
                    viewHolder.nativeAdSocialContext.setText(ad.getAdSocialContext());
                    viewHolder.nativeAdCallToAction.setVisibility(ad.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                    viewHolder.nativeAdCallToAction.setText(ad.getAdCallToAction());
                    viewHolder.nativeAdSponsoredLabel.setText(ad.getSponsoredTranslation());
                    View mNativeView = NativeAdView.render(context, ad, NativeAdView.Type.HEIGHT_300);
                    viewHolder.nativeAdContainer.removeAllViews();
                    viewHolder.nativeAdContainer.addView(mNativeView);

                }
                break;

            case 0:
                //Not really needed code here as we have the default.

            default:
                MyListHolder holder = (MyListHolder) holder1;
                Channel.Datum channelItem = (Channel.Datum) modelBase.get(position);
                int count = 0;

                if (isDark) {
                    holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.tool_bar_color));
                } else {
                    holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));

                }

                holder.channelViewTitle.setText(channelItem.name);
                holder.channelViewCategory.setText(channelItem.cat_name);
                Glide.with(holder.itemView)
                        .load(ADMIN_PANEL_URL + IMAGE_URL + channelItem.image)
                        .fitCenter()

                        .thumbnail(0.3f)
                        .into(holder.channelImageView);

                holder.itemView.setOnClickListener(v -> {
                    int idlist;
                    for (int i = 0; i < recentChannlView.size(); i++) {
                        if (recentChannlView.get(i).id == channelItem.id) {
                            idlist = i;
                            recentChannlView.remove(idlist);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(recentChannlView);
                            editor.putString("recent", json);
                            editor.apply();

                        }
                    }
                    if (channelItem.category_type == 1) {
                        recentChannlView.add(new Channel.Datum(channelItem.id, channelItem.cat_id, channelItem.url_id, channelItem.name, channelItem.image, channelItem.description, channelItem.url, channelItem.user_agent, channelItem.token, channelItem.extra, channelItem.created_at, channelItem.updated_at, channelItem.cat_name, channelItem.category_type));
                    }

                    if (channelItem.url_id == 9){
                        Intent intent = new Intent(v.getContext(), RadioActivity.class);
                        intent.putExtra("id", channelItem.id + "");
                        intent.putExtra("cat_id", channelItem.cat_id + "");
                        intent.putExtra("Title", channelItem.name);
                        intent.putExtra("Full", channelItem.description);
                        intent.putExtra("Image", channelItem.image);
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
                        ((Activity)context).finish();
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
                        Intent intent = new Intent("data-loaded");
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
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    }




                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(recentChannlView);
                    editor.putString("recent", json);
                    editor.apply();
                });


                holder.favoriteNullAdapter.setOnClickListener(v -> {
                    channelList.add(new Channel.Datum(channelItem.id, channelItem.cat_id, channelItem.url_id, channelItem.name, channelItem.image, channelItem.description, channelItem.url, channelItem.user_agent, channelItem.token, channelItem.extra, channelItem.created_at, channelItem.updated_at, channelItem.cat_name, channelItem.category_type));
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
        }
    }

    @Override
    public int getItemCount() {
        return modelBase.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object recyclerViewItem = modelBase.get(position);
        if (recyclerViewItem instanceof UnifiedNativeAd) {
            return 1;
        }
        if (recyclerViewItem instanceof com.facebook.ads.NativeAd) {
            fbNative = false;

            return 1;
        }
        return 0;
    }

    @Override
    public void onAdClick() {
        Log.d("TAG", "onAdClick: ");
    }

    @Override
    public void onAdFailed() {
        Log.d("TAG", "onAdFailed: ");
    }

    //ViewHolder for List Data
    public class MyListHolder extends RecyclerView.ViewHolder {

        ImageView channelImageView;
        TextView channelViewTitle;
        TextView channelViewCategory;
        TextView textViewOptions;
        ImageView favoriteNullAdapter;
        ImageView favoriteFullAdapter;
        CardView cardview;


        MyListHolder(View view) {
            super(view);
            channelImageView = itemView.findViewById(R.id.channelImageView);
            channelViewTitle = itemView.findViewById(R.id.channelViewTitle);
            channelViewCategory = itemView.findViewById(R.id.channelViewCategory);
            favoriteNullAdapter = itemView.findViewById(R.id.favorite_null_adapter);
            favoriteFullAdapter = itemView.findViewById(R.id.favorite_full_adapter);
            textViewOptions = itemView.findViewById(R.id.textViewOptions);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }

    //ViewHolder for Native Ad Data
    class UnifiedNativeAdViewHolder extends RecyclerView.ViewHolder {

        TextView ad_price;
        ImageView ad_icon;
        RatingBar ad_stars;
        TextView ad_store;
        TextView ad_advertiser;
        TextView ad_headline;
        TextView ad_body;
        CardView cardAdmobColor;

        UnifiedNativeAdViewHolder(View view) {
            super(view);
            cardAdmobColor = view.findViewById(R.id.cardAdmobColor);
            if (isDark) {
                cardAdmobColor.setCardBackgroundColor(ContextCompat.getColor(context, R.color.tool_bar_color));
            } else {
                cardAdmobColor.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
            ad_headline = view.findViewById(R.id.ad_headline);
            ad_body = view.findViewById(R.id.ad_body);
            ad_icon = view.findViewById(R.id.ad_icon);
            ad_price = view.findViewById(R.id.ad_price);
            ad_stars = view.findViewById(R.id.ad_stars);
            ad_store = view.findViewById(R.id.ad_store);
            ad_advertiser = view.findViewById(R.id.ad_advertiser);


        }
    }
    public class NativeAdViewHolder extends RecyclerView.ViewHolder {
        LinearLayout nativeAdContainer;
        LinearLayout adChoicesContainer;
        com.facebook.ads.MediaView nativeAdIcon;
        TextView nativeAdTitle;
        TextView nativeAdSponsoredLabel;
        TextView nativeAdSocialContext;
        TextView nativeAdBody;
        Button nativeAdCallToAction;
        ImageView testfbIcon;
        CardView cardview;



        NativeAdViewHolder(@NonNull View itemView) {
            super(itemView);
            nativeAdContainer = itemView.findViewById(R.id.adContainer);
            nativeAdIcon = itemView.findViewById(R.id.native_ad_icon);
            nativeAdTitle = itemView.findViewById(R.id.native_ad_title);
            nativeAdSponsoredLabel = itemView.findViewById(R.id.native_ad_sponsored_label);
            adChoicesContainer = itemView.findViewById(R.id.ad_choices_container);
            nativeAdSocialContext = itemView.findViewById(R.id.native_ad_social_context);
            nativeAdBody = itemView.findViewById(R.id.native_ad_body);
            nativeAdCallToAction = itemView.findViewById(R.id.native_ad_call_to_action);
            testfbIcon = itemView.findViewById(R.id.testfbIcon);
            cardview = itemView.findViewById(R.id.cardview);

        }
    }

}