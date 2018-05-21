package com.zerocool.biztrack.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zerocool.biztrack.Fragments.AllProductList;
import com.zerocool.biztrack.Fragments.CustomerList;
import com.zerocool.biztrack.Fragments.Home;

/**
 * Created by CrashOverride on 2/6/2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new Home();
            case 1:
                // Games fragment activity
                return new AllProductList();
            case 2:
                // Movies fragment activity
                return new CustomerList();
            default:
                return new Home();
        }

//        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}