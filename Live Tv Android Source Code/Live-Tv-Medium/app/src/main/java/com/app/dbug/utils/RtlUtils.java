package com.app.dbug.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;


import com.app.dbug.activity.SplashActivity;
import com.app.dbug.config.Constant;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class RtlUtils {

    public static void setScreenDirection(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        SharedPreferences sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        String locale = sharedPreferences.getString("lang", "English");

        if (Constant.ENABLE_RTL){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(new Locale(locale.toLowerCase()));
            } else {
                config.locale = new Locale(locale.toLowerCase());
            }
            resources.updateConfiguration(config, dm);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(new Locale("en"));
            } else {
                config.locale = new Locale(locale.toLowerCase());
            }
            resources.updateConfiguration(config, dm);
        }
    }

}
