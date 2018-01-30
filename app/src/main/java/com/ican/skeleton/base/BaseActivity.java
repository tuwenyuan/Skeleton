package com.ican.skeleton.base;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ican.skeleton.R;
import com.ican.skeleton.databinding.ViewBaseBinding;
import com.ican.skeleton.entity.IRequestNetData;
import com.ican.skeleton.entity.OnRetryClickListner;
import com.ican.skeleton.entity.RequestStatus;
import com.ican.skeleton.net.APIManagerService;
import com.ican.skeleton.net.HostType;
import com.ican.skeleton.net.NetRequestWork;
import com.ican.skeleton.net.RetrofitManager;
import com.ican.skeleton.utils.NetworkUtil;
import com.ican.skeleton.view.TitleView;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by twy on 2018/1/29.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public NetRequestWork netRequestWork = new NetRequestWork();
    public APIManagerService apiService = RetrofitManager.builder(HostType.TWY).mApiService;
    public ViewBaseBinding rtBinding;
    public RequestStatus status = RequestStatus.none;
    private IRequestNetData requestNetData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rtBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_base,null,false);
        setContentView(rtBinding.getRoot());
        rtBinding.flConetnt.addView(getContentView());
        initHeader(rtBinding.title);
        initData();
        initListener();
    }

    public void requestData(Observable observable, NetRequestWork.OnRequestListener listener){
        rtBinding.vError.setVisibility(View.GONE);
        rtBinding.vNoData.setVisibility(View.GONE);
        rtBinding.vNoNet.setVisibility(View.GONE);
        if(NetworkUtil.isAvailable(this)) {
            netRequestWork.requestData(observable, listener);
        }else{
            showNoNetView();
        }
    }

    public abstract View getContentView();
    public abstract void initHeader(TitleView titleView);
    public abstract void initData();
    public abstract void initListener();

    public void startRequestNetData(IRequestNetData requestNetData){
        this.requestNetData = requestNetData;
        this.requestNetData.loadNetData();
    }

    public void showErrorView(){
        status = RequestStatus.error;
        rtBinding.vError.setVisibility(View.VISIBLE);
        setTryAgainMthod();
    }

    public void showNoDataView(){
        status = RequestStatus.nodata;
        rtBinding.vNoData.setVisibility(View.VISIBLE);
        setTryAgainMthod();
    }

    public void showNoNetView(){
        status = RequestStatus.nonet;
        rtBinding.vNoNet.setVisibility(View.VISIBLE);
        setTryAgainMthod();
    }

    public void showLoading(boolean isBgTransparent){
        status = RequestStatus.loading;
        if(isBgTransparent){
            rtBinding.loading.rlLoading.setBackgroundColor(Color.parseColor("#01000000"));
        }else{
            rtBinding.loading.rlLoading.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        rtBinding.loading.rlNTLoad.setVisibility(View.VISIBLE);
    }

    public void hideLoding(){
        rtBinding.loading.rlNTLoad.setVisibility(View.GONE);
    }
    private OnRetryClickListner listner = new OnRetryClickListner() {
        @Override
        public void OnRetry(View view) {
            requestNetData.loadNetData();
        }
    };
    private void setTryAgainMthod(){
        if(requestNetData!=null){
            switch (status){
                case error:
                    rtBinding.vError.setOnRetryClickListner(listner);
                    break;
                case nonet:
                    rtBinding.vNoNet.setOnRetryClickListner(listner);
                    break;
                case nodata:
                    rtBinding.vNoData.setOnRetryClickListner(listner);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Subscriber s :netRequestWork.list) {
            s.unsubscribe();
        }
    }
}
