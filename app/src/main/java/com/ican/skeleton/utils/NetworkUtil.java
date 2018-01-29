package com.ican.skeleton.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by twy on 2018/1/29.
 */

public class NetworkUtil {

    /**
     * 判断网络是否可用
     * @return
     */
    public static boolean isAvailable(Context context){
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null ? false : activeNetworkInfo.isAvailable();
    }

}
