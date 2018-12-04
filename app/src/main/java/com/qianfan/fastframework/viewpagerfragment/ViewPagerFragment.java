package com.qianfan.fastframework.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfan.fastframework.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by wangjing on 2017/3/8.
 */

public class ViewPagerFragment extends SwipeBackFragment {
    private String[] mTitles = new String[]{"撸起袖子干", "种好辛福树", "常州之美"};

    private TabLayout tablayout;
    private ViewPager viewpager;
    private ViewPagerFragmentAdapter adapter;

    public static ViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        tablayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        viewpager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        adapter = new ViewPagerFragmentAdapter(getFragmentManager(), mTitles);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }
}
