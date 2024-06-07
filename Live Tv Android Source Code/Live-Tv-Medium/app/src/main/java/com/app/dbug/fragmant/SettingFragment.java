package com.app.dbug.fragmant;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dbug.config.Constant;
import com.codemybrainsout.ratingdialog.RatingDialog;
import com.app.dbug.R;
import com.app.dbug.activity.TvMainActivity;
import com.github.hamzaahmedkhan.spinnerdialog.SpinnerDialogFragment;
import com.github.hamzaahmedkhan.spinnerdialog.SpinnerModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.activity.SplashActivity.MYPREFERENCE;
import static com.app.dbug.activity.SplashActivity.PRIVACY_URL;
import static com.app.dbug.activity.SplashActivity.SUPPORT_EMAIL;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }
    ArrayList<SpinnerModel> arrSpinners = new ArrayList<>();
    LinearLayout languageSelect;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    TextView languView;
    Switch dark_mode_switch;
    Switch vibrator_switch;
    Switch notification_switch;
    LinearLayout feedBakId;
    LinearLayout faq_id;
    LinearLayout priv_id;
    LinearLayout tandc_id;
    LinearLayout reate_us;
    boolean isDark;
    LinearLayout main_layout_setting_fag;
    LinearLayout mainn_setting;
    LinearLayout toolBardId;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch listOrglide;
    TextView gridOrList;

    String privecy_url;
    String support_email;
    int spanCout;

    TextView dark_summery,about_summery,version_number,feedback_summery,faq_summery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        sharedPreferences = getContext().getSharedPreferences(MYPREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        languageSelect = view.findViewById(R.id.languageSelect);
        languView = view.findViewById(R.id.languView);
        dark_mode_switch = view.findViewById(R.id.dark_mode_switch);
        vibrator_switch = view.findViewById(R.id.vibrator_switch);
        notification_switch = view.findViewById(R.id.notification_switch);
        feedBakId = view.findViewById(R.id.feedBakId);
        faq_id = view.findViewById(R.id.faq_id);
        priv_id = view.findViewById(R.id.priv_id);
        tandc_id = view.findViewById(R.id.tandc_id);
        reate_us = view.findViewById(R.id.reate_us);
        listOrglide = view.findViewById(R.id.listOrglide);
        gridOrList = view.findViewById(R.id.gridOrList);
        main_layout_setting_fag = view.findViewById(R.id.main_layout_setting_fag);
        mainn_setting = view.findViewById(R.id.mainn_setting);
        toolBardId = view.findViewById(R.id.toolBardId);

        faq_summery = view.findViewById(R.id.faq_summery);
        feedback_summery = view.findViewById(R.id.feedback_summery);
        version_number = view.findViewById(R.id.version_number);
        about_summery = view.findViewById(R.id.about_summery);
        dark_summery = view.findViewById(R.id.dark_summery);

        if (isDark) {
            main_layout_setting_fag.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.maincolor));
            mainn_setting.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.maincolor));
            toolBardId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tool_bar_color));

            faq_summery.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            feedback_summery.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            version_number.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            about_summery.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            dark_summery.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            gridOrList.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
            languView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white_grey));
        } else {

            main_layout_setting_fag.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.main_color_w));
            mainn_setting.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.main_color_w));
            toolBardId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
        }


        String langu = sharedPreferences.getString("lang", "English");
        int listorglide = sharedPreferences.getInt("spanCout", 3);
        languView.setText(langu);

        arrSpinners.add(new SpinnerModel("English"));
        arrSpinners.add(new SpinnerModel("Arabic"));
        arrSpinners.add(new SpinnerModel("বাংলা"));
        arrSpinners.add(new SpinnerModel("Spanish"));
        arrSpinners.add(new SpinnerModel("Hindi"));

        if (listorglide == 1){
            gridOrList.setText(R.string.list);
        }else {
            gridOrList.setText(R.string.grid);
        }
        if (listorglide == 3) {
            listOrglide.setChecked(true);
        } else {
            listOrglide.setChecked(false);
        }
        listOrglide.setOnClickListener(v -> {
            if (listOrglide.isChecked()) {
                listOrglide.setChecked(true);
                editor.putInt("spanCout", 3);
                editor.apply();
                Intent intent = new Intent(getContext(), TvMainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            } else {
                listOrglide.setChecked(false);
                editor.putInt("spanCout", 1);
                editor.apply();
                Intent intent = new Intent(getContext(), TvMainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
        languageSelect.setOnClickListener(v -> {
            SpinnerDialogFragment spinnerDialogFragment = SpinnerDialogFragment.Companion.newInstance("Language", arrSpinners, (data, selectedPosition) -> {
                if (selectedPosition == 0){
                    editor.putString("lang", "en");
                    editor.apply();
                    languView.setText("English");
                }else if (selectedPosition == 1){
                    editor.putString("lang", "ar");
                    editor.apply();
                    languView.setText("Arabic");
                } else if (selectedPosition == 2){
                    editor.putString("lang", "bn");
                    editor.apply();
                    languView.setText("বাংলা");
                } else if (selectedPosition == 3){
                    editor.putString("lang", "es");
                    editor.apply();
                    languView.setText("Spanish");
                }else if (selectedPosition == 4){
                    editor.putString("lang", "hi");
                    editor.apply();
                    languView.setText("Hindi");
                }

                Intent intent = new Intent(getContext(), TvMainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }, 0);

            // Show Fragment
            spinnerDialogFragment.show(getActivity().getSupportFragmentManager(), "spinnerDialog");
        });

        dark_mode_switch.setOnClickListener(v -> {
            if (dark_mode_switch.isChecked()){
                dark_mode_switch.setChecked(true);
                editor.putBoolean("dark", true);
                editor.apply();
                Intent intent = new Intent(getContext(), TvMainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            else {
                dark_mode_switch.setChecked(false);
                editor.putBoolean("dark", false);
                editor.apply();
                Intent intent = new Intent(getContext(), TvMainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        vibrator_switch.setOnClickListener(v -> {
            if (vibrator_switch.isChecked()) {
                vibrator_switch.setChecked(true);
                editor.putBoolean("vibrator", true);
                editor.apply();
                // The toggle is enabled
            } else {
                vibrator_switch.setChecked(false);
                editor.putBoolean("vibrator", false);
                editor.apply();
                // The toggle is disabled
            }
        });
        notification_switch.setOnClickListener(v -> {
            if (notification_switch.isChecked()) {
                notification_switch.setChecked(true);
                editor.putBoolean("notification", true);
                editor.apply();
                // The toggle is enabled
            } else {
                notification_switch.setChecked(false);
                editor.putBoolean("notification", false);
                editor.apply();
                // The toggle is disabled
            }
        });
        dark_mode_switch.setChecked(isDark);
        boolean dar= sharedPreferences.getBoolean("dark", true);
        boolean vibrator= sharedPreferences.getBoolean("vibrator", false);
        boolean notifi= sharedPreferences.getBoolean("notification", false);

        privecy_url = sharedPreferences.getString(PRIVACY_URL,"");
        support_email = sharedPreferences.getString(SUPPORT_EMAIL,"");

        dark_mode_switch.setChecked(dar);
        vibrator_switch.setChecked(vibrator);
        notification_switch.setChecked(notifi);

        feedBakId.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={support_email};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_CC,recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Send Feedback - ["+getResources().getString(R.string.app_name)+"]");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        });

        faq_id.setOnClickListener(v -> openWebPage(Constant.FAQ_URL));
        priv_id.setOnClickListener(v -> openWebPage(privecy_url));
        tandc_id.setOnClickListener(v -> openWebPage(Constant.TERMS_URL));
        reate_us.setOnClickListener(v -> ratingDialog(getContext()));

        return view;
    }
    public void ratingDialog(Context context){
        final RatingDialog ratingDialog = new RatingDialog.Builder(context)
                .threshold(3)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.paypalColor)
                .negativeButtonTextColor(R.color.paypalColor)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.paypalColor)
                .playstoreUrl("YOUR_URL")
                .onThresholdCleared((ratingDialog1, rating, thresholdCleared) -> {
                    //do something
                    StringBuilder sb = new StringBuilder();
                    sb.append("market://details?id=");
                    sb.append(context.getPackageName());
                    String str = "android.intent.action.VIEW";
                    Intent intent = new Intent(str, Uri.parse(sb.toString()));

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("http://play.google.com/store/apps/details?id=");
                        sb2.append(context.getPackageName());
                        startActivity(new Intent(str, Uri.parse(sb2.toString())));
                    }
                    ratingDialog1.dismiss();
                })
                .onThresholdFailed((ratingDialog12, rating, thresholdCleared) -> {
                })
                .onRatingChanged((rating, thresholdCleared) -> {
                })
                .onRatingBarFormSumbit(feedback -> {
                }).build();

        ratingDialog.show();
    }


    public void openWebPage(String url) {

        Uri webpage = Uri.parse(url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}