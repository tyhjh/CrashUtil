package com.yorhp.crashlibrary.saveErro;

import com.yorhp.crashlibrary.CrashHander;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Tyhj on 2018/11/4 23:04
 * 邮箱：tyhj5@qq.com
 * github：github.com/tyhjh
 * description：SaveErroToSDCard
 */

public class SaveErroToSDCard implements ISaveErro {

    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private String saveErroDir;


    public SaveErroToSDCard(String saveErroDir) {
        this.saveErroDir = saveErroDir;
        File saveErroDirFile = new File(saveErroDir);
        if (!saveErroDirFile.exists()) {
            saveErroDirFile.mkdirs();
        }
    }

    @Override
    public void saveErroMsg(Throwable throwable) {
        try {
            // 获取当前时间
            long current = System.currentTimeMillis();
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
            // 创建存储异常信息的文件
            File file = new File(saveErroDir + FILE_NAME + time + FILE_NAME_SUFFIX);
            file.createNewFile();

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            pw.println(CrashHander.PHONE_INFO);
            throwable.printStackTrace(pw);   //输出异常信息
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
