package com.qianfan.fastframework.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.qianfan.fastframework.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by wangjing on 2017/3/8.
 */

public class ViewPagerFragmentActivity extends SwipeBackActivity {
    private String[] mTab = new String[]{"撸起袖子干", "种好辛福树", "常州之美"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagerfragment);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, ViewPagerFragment.newInstance());
        }
    }
}
