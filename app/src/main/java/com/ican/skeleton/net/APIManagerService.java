package com.ican.skeleton.net;

import com.ican.skeleton.entity.UgouCountBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by twy on 2018/1/29.
 */

public interface APIManagerService {
    String DOU_BAN_HOST = "http://jl.xuecheyi.com/api/";
    String SSB = "http://120.76.242.81:8080/ssb-app/user/";
    String MOKAO = "http://mokao.xuecheyi.com/api/";
    String UGOU = "http://test.ugou88.com/ugou-wx/";
    String TWY = "http://120.78.137.175/";

    @GET("Test/getRequestData")
    Observable<UgouCountBean> getPageData();


}
