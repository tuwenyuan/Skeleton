package com.ican.skeleton.base;

import android.app.Application;

/**
 * Created by twy on 2018/1/29.
 */

public class BaseApplication extends Application {

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
