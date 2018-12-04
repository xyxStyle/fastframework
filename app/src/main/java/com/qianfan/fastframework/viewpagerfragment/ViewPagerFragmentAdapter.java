package com.qianfan.fastframework.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wangjing on 2017/3/8.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public ViewPagerFragmentAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ViewPagerFragment1.newInstance();
            case 1:
                return ViewPagerFragment2.newInstance();
            case 2:
                return ViewPagerFragment3.newInstance();
            default:
                return ViewPagerFragment1.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
