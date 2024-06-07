package com.app.dbug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.dbug.radioservices.RadioPlayerService;


public class SleepTimeReceiver extends BroadcastReceiver {
    SharedPrefRadio sharedPrefRadio;

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPrefRadio = new SharedPrefRadio(context);

        if (sharedPrefRadio.getIsSleepTimeOn()) {
            sharedPrefRadio.setSleepTime(false, 0,0);
        }

        Intent intent_close = new Intent(context, RadioPlayerService.class);
        intent_close.setAction(RadioPlayerService.ACTION_STOP);
        context.startService(intent_close);
    }
}
