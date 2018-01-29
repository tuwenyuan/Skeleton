package com.ican.skeleton.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ican.skeleton.net.APIManagerService;
import com.ican.skeleton.net.HostType;
import com.ican.skeleton.net.NetRequestWork;
import com.ican.skeleton.net.RetrofitManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by twy on 2018/1/29.
 */

public class BaseActivity extends AppCompatActivity {

    public NetRequestWork netRequestWork = new NetRequestWork();
    public APIManagerService apiService = RetrofitManager.builder(HostType.TWY).mApiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void requestData(Observable observable, NetRequestWork.OnRequestListener listener){
        netRequestWork.requestData(observable,listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Subscriber s :netRequestWork.list) {
            s.unsubscribe();
        }
    }
}
