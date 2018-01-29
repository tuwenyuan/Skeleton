package com.ican.skeleton.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ican.skeleton.net.APIManagerService;
import com.ican.skeleton.net.NetRequestWork;

import rx.Observable;

/**
 * Created by twy on 2018/1/29.
 */

public class BaseFragment extends Fragment {

    public BaseActivity activity;
    public APIManagerService apiService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
        apiService = activity.apiService;
    }

    public void requestData(Observable observable, NetRequestWork.OnRequestListener listener){
        activity.netRequestWork.requestData(observable,listener);
    }
}
