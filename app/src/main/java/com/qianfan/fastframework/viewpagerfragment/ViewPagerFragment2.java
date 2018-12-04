package com.qianfan.fastframework.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianfan.fastframework.R;
import com.qianfan.fastframework.base.BaseFragment;

/**
 * Created by wangjing on 2017/3/8.
 */

public class ViewPagerFragment2 extends BaseFragment {
    private TextView tv_content;

    public static ViewPagerFragment2 newInstance() {
        Bundle args = new Bundle();
        ViewPagerFragment2 fragment = new ViewPagerFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textview, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_content = (TextView) view.findViewById(R.id.tv_content);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tv_content.setText("我是ViewPagerFragment2");
    }
}
