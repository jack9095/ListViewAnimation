package com.retrofit.wangfei.listviewanimation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.retrofit.wangfei.listviewanimation.R;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-24
 * Time: 12:26
 * Description:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ScrollingFragment())
                .commit();
    }
}
