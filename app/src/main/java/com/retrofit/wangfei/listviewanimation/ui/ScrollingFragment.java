package com.retrofit.wangfei.listviewanimation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.retrofit.wangfei.listviewanimation.R;
import com.retrofit.wangfei.listviewanimation.adapter.AnimatedArrayAdapter;
import com.retrofit.wangfei.listviewanimation.animator.AnimateScrollListener;
import com.retrofit.wangfei.listviewanimation.base.BaseFragment;
import com.retrofit.wangfei.listviewanimation.base.DatasetBuilder;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-24
 * Time: 12:26
 * Description: 显示ListView动画的Fragment
 */
public class ScrollingFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.list);
        final AnimatedArrayAdapter adapter = new AnimatedArrayAdapter(getActivity(), DatasetBuilder.buildLarge());
        listView.setOnScrollListener(new AnimateScrollListener(adapter)); // 滑动的时候的动画
        listView.setAdapter(adapter);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void initData() {}
}
