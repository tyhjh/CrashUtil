package com.yorhp.crashlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.yorhp.crashlibrary.saveErro.ISaveErro;

/**
 * 作者：Tyhj on 2018/11/4 22:50
 * 邮箱：tyhj5@qq.com
 * github：github.com/tyhjh
 * description：
 */

public class CrashUtil implements Thread.UncaughtExceptionHandler {


    public static String PHONE_INFO;
    ISaveErro mSaveErro;
    Context mContext;

    private Thread.UncaughtExceptionHandler mDefultCrashHandler;

    public static CrashUtil getInstance() {
        return CrashHanderHolder.crashHander;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        PHONE_INFO = savePhoneInfo(mContext);
        mDefultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void setmSaveErro(ISaveErro mSaveErro) {
        this.mSaveErro = mSaveErro;
    }

    /**
     * 当系统有未捕获的异常，会触发这个方法
     *
     * @param thread
     * @param throwable
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (mSaveErro != null) {
            mSaveErro.saveErroMsg(throwable);
        }

        if (mDefultCrashHandler != null) {
            mDefultCrashHandler.uncaughtException(thread, throwable);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    static class CrashHanderHolder {
        static CrashUtil crashHander = new CrashUtil();
    }

    // 保存手机的信息
    private String savePhoneInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer phoneMsg = new StringBuffer();
        phoneMsg.append("APP Version:")// APP的版本信息
                .append(pi.versionName + '_')
                .append(pi.versionCode + "\n")
                .append("OS Version:") // Android 手机版本号
                .append(Build.VERSION.RELEASE + '_')
                .append(Build.VERSION.SDK_INT + "\n")
                .append("Vendor:")// 手机制造商
                .append(Build.MANUFACTURER + "\n")
                .append("Model:")// 手机型号
                .append(Build.MODEL + "\n")
                .append("CUP ABI:" + Build.CPU_ABI + "\n");// CPU架构
        return phoneMsg.toString();
    }

}
