package com.ican.skeleton.net;

import android.util.SparseArray;

import com.ican.skeleton.BuildConfig;
import com.ican.skeleton.utils.FileUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by twy on 2018/1/29.
 */

public class RetrofitManager {

    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> mInstanceManager = new SparseArray<>(HostType.TYPE_COUNT);
    public APIManagerService mApiService;

    public static RetrofitManager builder(@HostTypeChecker int hostType){
        RetrofitManager instance = mInstanceManager.get(hostType);
        if(instance == null){
            instance = new RetrofitManager(hostType);
            mInstanceManager.put(hostType,instance);
        }
        return instance;
    }

    private RetrofitManager(@HostTypeChecker int hostType){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        Cache catche = new Cache(new File(FileUtil.getcacheDirectory(), "responses"), 10 * 1024 * 1024);
        okHttpClient.cache(catche).addNetworkInterceptor(cacheInterceptor);

        /**
         * 公共参数
         */
        QueryParameterInterceptor queryParameterInterceptor = new QueryParameterInterceptor();
        okHttpClient.addInterceptor(queryParameterInterceptor);
        /**
         * Log信息拦截器
         */
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? LoggingInterceptor.Level.BODY : LoggingInterceptor.Level.BASIC);
        okHttpClient.addInterceptor(loggingInterceptor);

        //设置超时
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        okHttpClient.retryOnConnectionFailure(true);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getHost(hostType))
                .build();
        mApiService = retrofit.create(APIManagerService.class);

    }

    private String getHost(int hostType) {
        switch (hostType) {
            case HostType.JIAO_LIAN:
                return APIManagerService.DOU_BAN_HOST;
            case HostType.MOKAO:
                return APIManagerService.MOKAO;
            case HostType.SSB:
                return APIManagerService.SSB;
            case HostType.UGOU:
                return APIManagerService.UGOU;
            case HostType.TWY:
                return APIManagerService.TWY;
            default:
                return null;
        }
    }

}
