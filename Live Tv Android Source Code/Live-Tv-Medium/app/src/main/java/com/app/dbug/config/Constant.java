package com.app.dbug.config;

import com.app.dbug.model.Channel;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class Constant {

        //settings url
        public static final String FAQ_URL = "https://www.google.com";
        public static final String TERMS_URL = "https://www.google.com";

        // Put Your Server Root Url Here
        public static final String ADMIN_PANEL_URL = "https://vid-mates.com";

        // Put Your Server subfolder as like this if you have any
//        public static final String ADMIN_SUB_URL = "/CodeCanyon/tv-chanel-v2";
        public static final String ADMIN_SUB_URL = "/abir/tv-chanel-v2";






        // NO CHANGE
        public static final String ADMIN_PANEL_PATH = ADMIN_SUB_URL+"/public/api";

        public static final String IMAGE_URL = ADMIN_SUB_URL+"/public/";
        public static final String CHANNEL_PANEL_URL = ADMIN_PANEL_PATH+"/channel";
        public static final String CATEGORY_PANEL_URL = ADMIN_PANEL_PATH+ "/category";
        public static final String CATEGORY_ALL_ITEM_URL = ADMIN_PANEL_PATH+ "/channel/category";
        public static final String HOME_DIALOG_URL = ADMIN_PANEL_PATH+ "/homeDialogueUrl";
        public static final String SEARCH_ITEM = ADMIN_PANEL_PATH+ "/channel/search";
        public static final String SLIDERS_PANEL_URL = ADMIN_PANEL_PATH+ "/slider";
        public static final String SINGLE_CHANNEL_URL = ADMIN_PANEL_PATH+"/channel";
        public static final String ADS_URL = ADMIN_PANEL_PATH+"/advertise";
        public static final String TOKEN = ADMIN_PANEL_PATH+"/store-token";
        public static final String SETTING_API = ADMIN_PANEL_PATH+"/settings";


        //enable RTL
        public static boolean ENABLE_RTL = true;
        public static String metadata;
        public static SimpleExoPlayer simpleExoPlayer;
        public static Boolean is_playing = false;
        public static Boolean radio_type = true;
        public static ArrayList<Channel.Datum> item_radio = new ArrayList<>();
        public static int position = 0;
        //radio will stop when receiving a phone call and will resume when the call ends
        public static final boolean RESUME_RADIO_ON_PHONE_CALL = true;
        //GDPR EU Consent

    }


