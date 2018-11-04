package com.yorhp.crashutil;

import android.app.Application;
import android.os.Environment;

import com.yorhp.crashlibrary.CrashHander;
import com.yorhp.crashlibrary.saveErro.SaveErroToSDCard;

/**
 * 作者：Tyhj on 2018/11/4 23:58
 * 邮箱：tyhj5@qq.com
 * github：github.com/tyhjh
 * description：
 */

public class MyApplication extends Application {

    public static String erroPath = Environment.getExternalStorageDirectory().getPath() + "/ACrashTest/log/";

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHander.getInstance().init(this, new SaveErroToSDCard(erroPath));
    }
}
