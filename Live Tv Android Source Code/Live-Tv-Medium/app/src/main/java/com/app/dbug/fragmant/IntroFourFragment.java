package com.app.dbug.fragmant;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.dbug.activity.TvMainActivity;
import com.app.dbug.R;
import com.app.dbug.SessionIntro;

public class IntroFourFragment extends Fragment {


    public IntroFourFragment() {
        // Required empty public constructor
    }

    TextView startNowButton;
    SessionIntro session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.welcome_slide4, container, false);

        session = new SessionIntro(getContext());

        startNowButton = view.findViewById(R.id.startNowButton);
        startNowButton.setOnClickListener(v -> launchHomeScreen());
        return view;
    }
    private void launchHomeScreen() {
        session.setFirstTimeLaunch(false);
        startActivity(new Intent(getContext(), TvMainActivity.class));
        getActivity().finish();
    }
}