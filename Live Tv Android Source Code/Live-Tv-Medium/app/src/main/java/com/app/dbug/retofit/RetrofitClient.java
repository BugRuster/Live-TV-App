package com.app.dbug.retofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;

public class RetrofitClient {

    static  Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit != null){
            return retrofit;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(ADMIN_PANEL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
