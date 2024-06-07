package com.app.dbug.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app.dbug.fragmant.IntroFourFragment;
import com.app.dbug.fragmant.IntroOneFragment;
import com.app.dbug.fragmant.IntroTreFragment;
import com.app.dbug.fragmant.IntroTwoFragment;

public class viewPager extends FragmentPagerAdapter {

    public viewPager(@NonNull FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new IntroOneFragment();    // Fragment1 is the name of first blank fragment,
            // you can replace its name with your created fragment name
            case 1:
                return new IntroTwoFragment();    // Fragment2 is the name of second blank fragment,
            // you can replace its name with your created fragment name
            case 2:
                return new IntroTreFragment();    // Fragment1 is the name of first blank fragment,
            // you can replace its name with your created fragment name
            case 3:
                return new IntroFourFragment();    // Fragment2 is the name of second blank fragment,
            // you can replace its name with your created fragment name
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
