package com.app.dbug.utils;


import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.app.dbug.SharedPrefRadio;
import com.app.dbug.config.Constant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    Context _context;
    SharedPrefRadio sharedPrefRadio;

    public Tools(Context context) {
        this._context = context;
        sharedPrefRadio = new SharedPrefRadio(context);
    }

    public void showToast(String msg) {
        Toast.makeText(_context, msg, Toast.LENGTH_LONG).show();
    }

    public long convertToMilliSeconds(String s) {

        long ms = 0;
        Pattern p;
        if (s.contains(("\\:"))) {
            p = Pattern.compile("(\\d+):(\\d+)");
        } else {
            p = Pattern.compile("(\\d+).(\\d+)");
        }
        Matcher m = p.matcher(s);
        if (m.matches()) {
            int h = Integer.parseInt(m.group(1));
            int min = Integer.parseInt(m.group(2));
            ms = (long) h * 60 * 60 * 1000 + min * 60 * 1000;
        }
        return ms;
    }

    public void getPosition(Boolean isNext) {
        if (isNext) {
            if (Constant.position != Constant.item_radio.size() - 1) {
                Constant.position = Constant.position + 1;
            } else {
                Constant.position = 0;
            }
        } else {
            if (Constant.position != 0) {
                Constant.position = Constant.position - 1;
            } else {
                Constant.position = Constant.item_radio.size() - 1;
            }
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}