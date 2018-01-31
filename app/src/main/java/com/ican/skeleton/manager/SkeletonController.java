package com.ican.skeleton.manager;

import android.os.Handler;
import android.os.Looper;

import com.ican.skeleton.listener.CartRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twy on 2018/1/31.
 */

public class SkeletonController {
    private static SkeletonController instance;
    public static Handler mMainHandler = new Handler(Looper.getMainLooper());

    private List<CartRefreshListener> cartRefreshListenerList = new ArrayList<>();

    public static SkeletonController getInstance(){
        if(null == instance) {
            synchronized (SkeletonController.class) {
                if (null == instance) {
                    instance = new SkeletonController();
                }
            }
        }
        return instance;
    }

    public void registCartNumRefreshListener(CartRefreshListener listener){
        if(!cartRefreshListenerList.contains(listener)){
            cartRefreshListenerList.add(listener);
        }
    }

    public void unRegistCartNumRefreshListener(CartRefreshListener listener){
        if(cartRefreshListenerList.contains(listener)){
            cartRefreshListenerList.remove(listener);
        }
    }

    public synchronized void postCarNumRefreshCallBack(final int num){
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                for(CartRefreshListener listener : cartRefreshListenerList){
                    listener.cartNumRefresh(num);
                }
            }
        });
    }

}
