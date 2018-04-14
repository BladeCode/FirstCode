package org.incoder.uiwidget;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * BasePagerAdapter
 *
 * @author Jerry xu
 * @date 4/6/2018 6:00 AM.
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private String[] mTitles;

    public BasePagerAdapter(FragmentManager fm,
                            ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public BasePagerAdapter(FragmentManager fm,
                            ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public BasePagerAdapter(FragmentManager fm,
                            ArrayList<Fragment> fragments, String[] mTitles) {
        super(fm);
        this.fragments = fragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null || titles.size() == 0) {
            return mTitles == null ? "" : mTitles[position];
        } else if (mTitles == null || mTitles.length == 0) {
            return titles.get(position);
        } else {
            return "";
        }
    }
}
