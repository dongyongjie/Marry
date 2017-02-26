package com.bwie.marry;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by lenovo on 2017/2/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
