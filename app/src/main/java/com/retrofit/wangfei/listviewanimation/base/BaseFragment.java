package com.retrofit.wangfei.listviewanimation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.wangfei.listviewanimation.R;


/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-24
 * Time: 12:26
 * Description:
 */
public abstract class BaseFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater,container);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    public abstract void initData();
}
