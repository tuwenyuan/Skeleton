package com.ican.skeleton.activity;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import com.ican.skeleton.R;
import com.ican.skeleton.base.BaseActivity;
import com.ican.skeleton.base.BaseApplication;
import com.ican.skeleton.databinding.ActivityMainBinding;
import com.ican.skeleton.entity.IRequestNetData;
import com.ican.skeleton.entity.UgouCountBean;
import com.ican.skeleton.net.NetRequestWork;
import com.ican.skeleton.view.TitleView;

import java.util.Random;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    public View getContentView() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main,null,false);
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initHeader(TitleView titleView) {
        titleView.showTitleView(R.mipmap.ic_launcher, "左边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"左边点击了",Toast.LENGTH_SHORT).show();
            }
        }, "标题", null, "右边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"右边点击了",Toast.LENGTH_SHORT).show();
            }
        },true);
    }

    @Override
    public void initListener() {
        binding.tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRequestNetData(new IRequestNetData() {
                    @Override
                    public void loadNetData() {
                        loadNetData1();
                    }
                });
            }
        });
    }

    public void loadNetData1(){
        final Random rd = new Random();
        showLoading(false);//true代表加载中的视图背景是透明的
        requestData(apiService.getPageData(), new NetRequestWork.OnRequestListener<UgouCountBean>() {
            @Override
            public void onRecvDataBack(final UgouCountBean ugouCountBean) {
                BaseApplication.mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = rd.nextInt(10);
                        if(i>0 && i<=3){////假设1~3网络数据请求成功
                            Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                            hideLoding();
                            rtBinding.flConetnt.setVisibility(View.VISIBLE);
                        }else if(i>3 && i<=6){//4~6服务器报异常
                            //throw new CustomDataException("模拟异常~~");
                            hideLoding();
                            showErrorView();
                        }else{//其它为服务器返回空数据
                            hideLoding();
                            showNoDataView();
                        }
                    }
                },2000);
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                hideLoding();
                showErrorView();
            }
        });
    }
}



