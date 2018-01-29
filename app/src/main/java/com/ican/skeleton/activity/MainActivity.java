package com.ican.skeleton.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ican.skeleton.R;
import com.ican.skeleton.base.BaseActivity;
import com.ican.skeleton.databinding.ActivityMainBinding;
import com.ican.skeleton.entity.UgouCountBean;
import com.ican.skeleton.net.NetRequestWork;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main,null,false);
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        binding.tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*RetrofitManager.builder(HostType.TWY).mApiService.getPageData()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<UgouCountBean>() {
                            @Override
                            public void call(UgouCountBean ugouCountBean) {
                                Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(MainActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });*/
                requestData(apiService.getPageData(), new NetRequestWork.OnRequestListener<UgouCountBean>() {
                    @Override
                    public void onRecvDataBack(UgouCountBean ugouCountBean) {
                        Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
