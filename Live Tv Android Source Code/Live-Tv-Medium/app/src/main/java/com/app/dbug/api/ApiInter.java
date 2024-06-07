package com.app.dbug.api;


import com.app.dbug.config.Constant;
import com.app.dbug.model.Ads;
import com.app.dbug.model.Category;
import com.app.dbug.model.Channel;
import com.app.dbug.model.DeviceToken;
import com.app.dbug.model.HomeDiloagModel;
import com.app.dbug.model.SettingModle;
import com.app.dbug.model.UserAgentModel;
import com.app.dbug.model.Slider;
import com.app.dbug.model.TokenModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


public interface ApiInter {


    @GET(Constant.CATEGORY_PANEL_URL)
    Call<Category> getCategoryBody();

    @GET(Constant.HOME_DIALOG_URL)
    Call<HomeDiloagModel> getHomeDilogBody();

    @GET(Constant.ADS_URL)
    Call<Ads> getAds();

    @GET(Constant.CHANNEL_PANEL_URL)
    Call<Channel> getChannelBody();

    @GET(Constant.SINGLE_CHANNEL_URL +"/{id}")
    Call<Channel> getSingleChannelBody(
            @Path("id") String cid
    );

    @GET(Constant.SLIDERS_PANEL_URL)
    Call<Slider> getSlider();


    @GET
    Call<TokenModel> getToken(@Url String url);

    @GET
    Call<UserAgentModel> getUserAgent(@Url String url);

    @GET(Constant.SETTING_API)
    Call<SettingModle> getSettingData();

    @GET(Constant.CATEGORY_ALL_ITEM_URL+"/{id}")
    Call<Channel> getChannelInCategory(
            @Path("id") String cid
    );
    @GET(Constant.SEARCH_ITEM+"/{searchkey}")
    Call<Channel> getChannalSearch(
            @Path("searchkey") String cid
    );

    @POST(Constant.TOKEN)
    Call<DeviceToken> postToken(@Body DeviceToken tokenModel);


}
