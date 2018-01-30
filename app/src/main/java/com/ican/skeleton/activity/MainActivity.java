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
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadNetData1(){
        final Random rd = new Random();
        showLoading(true);
        requestData(apiService.getPageData(), new NetRequestWork.OnRequestListener<UgouCountBean>() {
            @Override
            public void onRecvDataBack(final UgouCountBean ugouCountBean) {
                BaseApplication.mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = rd.nextInt(10);
                        if(i>0 && i<=3){
                            Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                            hideLoding();
                        }else if(i>3 && i<=6){
                            //throw new CustomDataException("模拟异常~~");
                            hideLoding();
                            showErrorView();
                        }else{
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
