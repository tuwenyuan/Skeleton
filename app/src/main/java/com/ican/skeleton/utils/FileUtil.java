package com.ican.skeleton.utils;

import com.ican.skeleton.base.BaseApplication;

import java.io.File;

/**
 * Created by twy on 2018/1/29.
 */

public class FileUtil {

    /**
     * @return  创建缓存目录
     */
    public static File getcacheDirectory()
    {
        File file = new File(BaseApplication.instance.getApplicationContext().getExternalCacheDir(), "SkelectonCache");
        if(!file.exists())
            file.mkdirs();
        return file;
    }

}
