package com.ican.skeleton.base;

import android.app.Application;
import android.os.Handler;

/**
 * Created by twy on 2018/1/29.
 */

public class BaseApplication extends Application {

    public static Application instance;
    public static Handler mhandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
